package com.invictusqiu.eduorder.client;

import com.invictusqiu.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @description: 课程信息远程调用接口
 * @className: EduClient.java
 * @author: qqdas
 * @createTime: 2023/10/15 12:58
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    // 根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);
}
