package com.invictusqiu.eduorder.service.impl;

import com.invictusqiu.commonutils.ordervo.CourseWebVoOrder;
import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.eduorder.client.EduClient;
import com.invictusqiu.eduorder.client.UcenterClient;
import com.invictusqiu.eduorder.entity.Order;
import com.invictusqiu.eduorder.mapper.OrderMapper;
import com.invictusqiu.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.invictusqiu.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    // 1.生成订单的方法
    @Override
    public String createOrder(String courseId, String memberIdByJwtToken) {
        // 通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberIdByJwtToken);

        // 通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        //创建Order对象，向order对象里面设置需要数据
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo()); //订单号
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberIdByJwtToken);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0); //订单状态(0:未支付，1:已支付)
        order.setPayType(1); //支付类型：微信1
        baseMapper.insert(order);
        // 返回订单号
        return order.getOrderNo();
    }
}
