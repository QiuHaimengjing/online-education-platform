package com.invictusqiu.msmservice.service;

import java.util.Map;

/**
 * @description: 短信验证服务接口
 * @className: MsmService.java
 * @author: qqdas
 * @createTime: 2023/10/12 11:02
 */
public interface MsmService {
    // 发送短信的方法
    boolean send(Map<String, Object> param, String phone);
}
