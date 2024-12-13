package com.invictusqiu.canal;

import com.invictusqiu.canal.client.CanalClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @description: 数据同步启动类
 * @className: CanalApplication.java
 * @author: qqdas
 * @createTime: 2023/10/19 11:12
 */
@SpringBootApplication
public class CanalApplication implements CommandLineRunner {

    @Resource
    private CanalClient canalClient;

    public static void main(String[] args) {
        SpringApplication.run(CanalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 项目启动，执行canal客户端监听
        canalClient.run();
    }
}
