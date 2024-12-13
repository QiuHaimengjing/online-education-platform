package com.invictusqiu.educenter.controller;


import com.invictusqiu.commonutils.JwtUtils;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.educenter.entity.UcenterMember;
import com.invictusqiu.educenter.entity.vo.LoginVo;
import com.invictusqiu.educenter.entity.vo.RegisterVo;
import com.invictusqiu.educenter.service.UcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-12
 */
@RestController
@RequestMapping("/educenter/member")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService memberService;

    // 登录的方法
    @PostMapping("login")
    public Result loginUser(@RequestBody LoginVo loginVo) {
        // 调用service方法实现登录
        // 返回token值，使用jwt生成
        String token = memberService.login(loginVo);
        return Result.ok().data("token",token);
    }

    // 注册的方法
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return Result.ok();
    }

    // 根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        // 调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        // 查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return Result.ok().data("userInfo",member);
    }

    // 根据用户id获取用户信息
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        // 把member对象里面值复制给UcenterMemberOrder对象
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    // 查询某一天注册人数
    @GetMapping("countRegister/{day}")
    public Result countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return Result.ok().data("countRegister",count);
    }

}

