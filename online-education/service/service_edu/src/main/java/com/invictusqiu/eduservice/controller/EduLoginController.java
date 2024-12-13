package com.invictusqiu.eduservice.controller;

import com.invictusqiu.commonutils.Result;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 登录控制器（已弃用，现更换为Spring Security）
 * @className: EduLoginController.java
 * @author: qqdas
 * @createTime: 2023/10/4 10:06
 */
@RestController
@RequestMapping("/eduservice/user")
//@CrossOrigin //解决跨域
public class EduLoginController {

    // login
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token","admin");
    }

    // info
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
