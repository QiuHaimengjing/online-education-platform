package com.invictusqiu.eduorder.service;

import com.invictusqiu.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-15
 */
public interface OrderService extends IService<Order> {

    // 1.生成订单的方法
    String createOrder(String courseId, String memberIdByJwtToken);
}
