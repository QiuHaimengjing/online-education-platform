package com.invictusqiu.eduservice.client;

import com.invictusqiu.eduservice.client.impl.OrderFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description: 查询课程购买状态远程调用接口
 * @className: OrdersClient.java
 * @author: qqdas
 * @createTime: 2023/10/16 17:05
 */
@Component
@FeignClient(value = "service-order", fallback = OrderFileDegradeFeignClient.class)
public interface OrdersClient {

    // 根据课程id和用户id查询订单表中订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);
}
