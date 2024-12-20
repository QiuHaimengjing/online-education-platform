package com.invictusqiu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 常量读取工具类
 * @className: ConstantPropertiesUtils.java
 * @author: qqdas
 * @createTime: 2023/10/6 10:23
 */
// 当项目已启动，spring接口，spring加载之后，执行接口一个方法
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    // 读取配置文件application.properties中的配置
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.buckername}")
    private String bucketName;

    // 定义公开静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
