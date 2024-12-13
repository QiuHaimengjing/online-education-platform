package com.invictusqiu.eduorder.client;

import com.invictusqiu.commonutils.ordervo.UcenterMemberOrder;
import com.invictusqiu.servicebase.exceptionhandler.EduException;

/**
 * @description: 用户信息远程调用实现类，熔断器，失败时执行
 * @className: UcenterFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/23 11:46
 */

public class UcenterFileDegradeFeignClient implements UcenterClient {
    @Override
    public UcenterMemberOrder getUserInfoOrder(String id) {
        throw new EduException(20001,"用户信息调用失败");
    }
}
