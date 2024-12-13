package com.invictusqiu.eduservice.client.impl;

import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.eduservice.client.UcenterClient;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import org.springframework.stereotype.Component;

/**
 * @description: 熔断器实现类，出错时执行
 * @className: UcenterFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/31 19:39
 */
@Component
public class UcenterFileDegradeFeignClient implements UcenterClient {
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        throw new EduException(20001,"获取用户信息失败");
    }
}
