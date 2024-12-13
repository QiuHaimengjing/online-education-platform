package com.invictusqiu.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @description: 短信验证启动类
 * @className: MsmApplication.java
 * @author: qqdas
 * @createTime: 2023/10/12 10:59
 */
@EnableDiscoveryClient  // 注册中心nacos注册
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class) //取消数据源自动配置
@ComponentScan("com.invictusqiu")
public class MsmApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
