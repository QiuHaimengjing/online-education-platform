package com.invictusqiu.educenter.service;

import com.invictusqiu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.invictusqiu.educenter.entity.vo.LoginVo;
import com.invictusqiu.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-12
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    // 登录的方法
    // 返回token值，使用jwt生成
    String login(LoginVo loginVo);

    // 注册的方法
    void register(RegisterVo registerVo);

    // 判断数据表里面是否存在相同微信信息，根据openid判断
    UcenterMember getOpenIdMember(String openid);

    // 查询某一天注册人数
    Integer countRegisterDay(String day);
}
