package com.invictusqiu.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 订单启动类
 * @className: OrderApplication
 * @author: qqdas
 * @createTime: 2023/10/15
 */
@SpringBootApplication
@EnableDiscoveryClient // 注册中心nacos注册
@EnableFeignClients
@ComponentScan(basePackages = {"com.invictusqiu"})
@MapperScan("com.invictusqiu.eduorder.mapper")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}