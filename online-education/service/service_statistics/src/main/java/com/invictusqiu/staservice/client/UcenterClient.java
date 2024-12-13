package com.invictusqiu.staservice.client;

import com.invictusqiu.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 远程调用Ucenter接口
 * @className: UcenterClient.java
 * @author: qqdas
 * @createTime: 2023/10/17 12:43
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    // 查询某一天注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable("day") String day);
}
