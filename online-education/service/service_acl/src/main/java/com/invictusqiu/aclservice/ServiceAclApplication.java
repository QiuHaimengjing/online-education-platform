package com.invictusqiu.aclservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 权限管理模块启动类
 * @className: ServiceAclApplication.java
 * @author: qqdas
 * @createTime: 2023/10/20 16:59
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.invictusqiu")
@MapperScan("com.invictusqiu.aclservice.mapper")
public class ServiceAclApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }
}
