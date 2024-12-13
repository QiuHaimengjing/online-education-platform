package com.invictusqiu.eduservice.client;

import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.eduservice.client.impl.UcenterFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 课程评论模块远程调用用户信息接口
 * @className: UcenterClient.java
 * @author: qqdas
 * @createTime: 2023/10/31 19:37
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UcenterFileDegradeFeignClient.class)
public interface UcenterClient {

    // 定义调用的方法路径
    // 根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
