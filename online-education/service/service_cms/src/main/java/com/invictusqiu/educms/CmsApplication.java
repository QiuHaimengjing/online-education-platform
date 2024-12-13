package com.invictusqiu.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 前台内容管理模块启动类
 * @className: CmsApplication.java
 * @author: qqdas
 * @createTime: 2023/10/11 16:46
 */
@SpringBootApplication
@EnableDiscoveryClient  // 注册中心nacos注册
@ComponentScan({"com.invictusqiu"}) // 指定扫描位置
@MapperScan("com/invictusqiu/educms/mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
