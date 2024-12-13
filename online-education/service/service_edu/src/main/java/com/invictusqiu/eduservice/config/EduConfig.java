package com.invictusqiu.eduservice.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @description: 讲师管理模块的配置类
 * @className: EduConfig.java
 * @author: qqdas
 * @createTime: 2023/9/30 18:42
 */
@Configuration
@MapperScan("com.invictusqiu.eduservice.mapper")
public class EduConfig {

    /**
     * @description: SQL 执行性能分析插件
     * 开发环境使用
     * @author: qqdas
     * @time: 2023/9/30 18:49
     */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor(){
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000); //ms，超过此处设置的ms则SQL不执行
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    /**
     * @description: 逻辑删除插件
     * @author: qqdas
     * @time: 2023/9/30 19:14
     */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /**
     * @description: 分页插件
     * @author: qqdas
     * @time: 2023/10/1 11:53
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
