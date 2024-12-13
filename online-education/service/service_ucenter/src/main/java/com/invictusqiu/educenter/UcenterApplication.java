package com.invictusqiu.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 会员中心启动类
 * @className: UcenterApplication.java
 * @author: qqdas
 * @createTime: 2023/10/12 13:42
 */
@EnableDiscoveryClient  // 注册中心nacos注册
@ComponentScan({"com.invictusqiu"})
@SpringBootApplication
@MapperScan("com.invictusqiu.educenter.mapper")
public class UcenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
