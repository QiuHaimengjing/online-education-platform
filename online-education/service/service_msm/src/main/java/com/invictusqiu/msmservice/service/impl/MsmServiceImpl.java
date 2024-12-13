package com.invictusqiu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.invictusqiu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @description: 短信验证服务实现
 * @className: MsmServiceImpl.java
 * @author: qqdas
 * @createTime: 2023/10/12 11:03
 */
@Service
public class MsmServiceImpl implements MsmService {

    // 发送短信的方法
    @Override
    public boolean send(Map<String, Object> param, String phone) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "", "");
        IAcsClient client = new DefaultAcsClient(profile);

        // 设置相关固定参数
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");

        // 设置发送相关的参数
        request.putQueryParameter("PhoneNumbers", phone);   // 手机号
        request.putQueryParameter("SignName", "邱海俊的毕业设计网站");  // 申请阿里云的签名名称
        request.putQueryParameter("TemplateCode", "SMS_463587572");    // 申请阿里云的模板code
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); // 验证码数据，转成json数据传递


        try {
            // 最终发送
            CommonResponse response = client.getCommonResponse(request);
            boolean success = response.getHttpResponse().isSuccess();
            return success;
        } catch (Exception e) {

        }
        return false;
    }
}
