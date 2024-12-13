package com.invictusqiu.eduservice.client.impl;

import com.invictusqiu.eduservice.client.OrdersClient;
import org.springframework.stereotype.Component;

/**
 * @description: 熔断器的实现类，出错时执行
 * @className: OrderFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/17 10:34
 */
@Component
public class OrderFileDegradeFeignClient implements OrdersClient {
    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
