package com.invictusqiu.educenter.controller;

import com.google.gson.Gson;
import com.invictusqiu.commonutils.JwtUtils;
import com.invictusqiu.educenter.entity.UcenterMember;
import com.invictusqiu.educenter.service.UcenterMemberService;
import com.invictusqiu.educenter.utils.ConstantWxUtils;
import com.invictusqiu.educenter.utils.HttpClientUtils;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.util.HashMap;

/**
 * @description: 微信扫码登录控制器
 * @className: WxApiController.java
 * @author: qqdas
 * @createTime: 2023/10/13 14:02
 */
@Controller //注意这里没有配置 @RestController，不需要返回数据，只请求地址
@RequestMapping("/api/ucenter/wx")
//@CrossOrigin
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    // 获取扫描人信息，添加数据
    @GetMapping("callback")
    public String callback(String code, String state) {

        try {
            // 1.获取code值，临时票据，类似于验证码

            // 2.拿着code请求 微信固定的地址，得到两个值 access_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";

            // 拼接三个参数：id 密钥 和 code值
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            // 请求这个拼接好的地址，得到返回两个值 access_token 和 openid
            // 使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            // 从accessTokenInfo字符串获取出来两个值 access_token 和 openid
            // 把accessTokenInfo字符串转换map集合，根据map里面key获取对应值
            // 使用json转换工具 Gson
            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String access_token = (String)mapAccessToken.get("access_token");
            String openid = (String)mapAccessToken.get("openid");

            // 把扫描人信息添加数据库里面
            // 判断数据表里面是否存在相同微信信息，根据openid判断
            UcenterMember member = memberService.getOpenIdMember(openid);
            if (member == null) {   //member是空，表没有相同微信数据，进行添加
                // 3.拿着得到access_token 和 openid，再去请求微信提供的固定地址，获取到扫描人信息
                // 访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                // 拼接两个参数
                String userInfoUrl = String.format(
                        baseUserInfoUrl,
                        access_token,
                        openid
                );
                // 发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);
                // 获取返回userInfo字符串扫描人信息
                HashMap userInfoMap = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)userInfoMap.get("nickname");  //昵称
                String headimgurl = (String)userInfoMap.get("headimgurl");  //头像

                member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }

            // 使用jwt根据member对象生成token字符串
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
            // 最后：回到首页面，通过路径传递token字符串
            return "redirect:http://localhost:3000?token="+jwtToken;
        } catch (Exception e) {
            throw new EduException(20001,"登陆失败");
        }

    }


    @GetMapping("login")
    public String getWxCode() {
        // 微信开放平台授权baseUrl %s相当于?代表占位符
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 对redirect_url进行URLEncoder编码
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL;
        try {
            redirectUrl = URLEncoder.encode(redirectUrl,"utf-8");
        } catch (Exception e) {
        }

        String url = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                "invictusqiu"
                );

        // 重定向到请求微信地址里面
        return "redirect:"+url;
    }
}
