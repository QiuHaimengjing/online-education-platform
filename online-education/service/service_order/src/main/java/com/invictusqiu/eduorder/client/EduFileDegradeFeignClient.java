package com.invictusqiu.eduorder.client;

import com.invictusqiu.commonutils.ordervo.CourseWebVoOrder;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import org.springframework.stereotype.Component;

/**
 * @description: 课程信息远程调用实现类，熔断器，失败时执行
 * @className: EduFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/23 11:45
 */
@Component
public class EduFileDegradeFeignClient implements EduClient {
    @Override
    public CourseWebVoOrder getCourseInfoOrder(String id) {
        throw new EduException(20001,"课程信息调用失败");
    }
}
