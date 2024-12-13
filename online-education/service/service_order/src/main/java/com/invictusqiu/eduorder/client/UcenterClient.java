package com.invictusqiu.eduorder.client;

import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 用户信息远程调用接口
 * @className: UcenterClient.java
 * @author: qqdas
 * @createTime: 2023/10/15 13:01
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    // 根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
