package com.invictusqiu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 讲师和课程后台管理启动类
 * @className: EduApplication.java
 * @author: qqdas
 * @createTime: 2023/9/30 18:39
 */
@SpringBootApplication
@EnableDiscoveryClient  // 注册中心nacos注册
@EnableFeignClients // 服务调用的调用端
@ComponentScan(basePackages = {"com.invictusqiu"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
