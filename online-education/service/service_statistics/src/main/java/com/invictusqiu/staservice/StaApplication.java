package com.invictusqiu.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description: 统计分析模块启动类
 * @className: StaApplication.java
 * @author: qqdas
 * @createTime: 2023/10/17 12:15
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.invictusqiu"})
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.invictusqiu.staservice.mapper")
@EnableScheduling //定时任务注解
public class StaApplication {

    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
