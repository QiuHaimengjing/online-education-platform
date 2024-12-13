package com.invictusqiu.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.invictusqiu.commonutils.JwtUtils;
import com.invictusqiu.commonutils.MD5;
import com.invictusqiu.educenter.entity.UcenterMember;
import com.invictusqiu.educenter.entity.vo.LoginVo;
import com.invictusqiu.educenter.entity.vo.RegisterVo;
import com.invictusqiu.educenter.mapper.UcenterMemberMapper;
import com.invictusqiu.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-12
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    // 注入Redis
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 登录的方法
    // 返回token值，使用jwt生成
    @Override
    public String login(LoginVo loginVo) {
        // 获取登录手机号和密码
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // 手机号和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new EduException(20001,"登陆失败");
        }

        // 判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);

        // 判断查询对象是否为空
        if (mobileMember == null) { //没有这个手机号
            throw new EduException(20001,"登陆失败");
        }

        // 判断密码
        // 加密方式 MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new EduException(20001,"登陆失败");
        }

        // 判断用户是否禁用
        if (mobileMember.getIsDisabled()) {
            throw new EduException(20001,"登陆失败");
        }

        // 登陆成功
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());

        return jwtToken;
    }

    // 注册的方法
    @Override
    public void register(RegisterVo registerVo) {
        // 获取注册的数据
        String code = registerVo.getCode(); //验证码
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码

        // 非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
            || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new EduException(20001,"注册失败");
        }

        // 判断验证码
        // 获取redis验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(redisCode)) {
            throw new EduException(20001,"注册失败");
        }

        // 判断手机号是否重复，表里面存在相同的手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new EduException(20001,"注册失败");
        }

        // 数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);    //用户不禁用
        member.setAvatar("https://invictusqiu.oss-cn-beijing.aliyuncs.com/2023/10/07/daf06f2050d94ebcbdac21ab5abef083edu_03.jpg");
        baseMapper.insert(member);
    }

    // 判断数据表里面是否存在相同微信信息，根据openid判断
    @Override
    public UcenterMember getOpenIdMember(String openid) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openid);
        UcenterMember member = baseMapper.selectOne(wrapper);
        return member;
    }

    // 查询某一天注册人数
    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }
}
