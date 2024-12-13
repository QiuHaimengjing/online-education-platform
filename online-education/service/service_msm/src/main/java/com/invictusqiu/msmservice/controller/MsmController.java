package com.invictusqiu.msmservice.controller;

import com.invictusqiu.commonutils.Result;
import com.invictusqiu.msmservice.service.MsmService;
import com.invictusqiu.msmservice.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description: 短信验证控制器
 * @className: MsmController.java
 * @author: qqdas
 * @createTime: 2023/10/12 10:59
 */
@RestController
@RequestMapping("/edumsm/msm")
//@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    // springboot整合的Redis模板对象
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 发送短信的方法
    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone) {
        // 1.从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.ok();
        }

        // 2.如果redis获取不到，进行阿里云发送
        // 生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param = new HashMap<>();
        param.put("code",code);
        // 调用service发送短信的方法
        boolean isSend = msmService.send(param,phone);
        if (isSend) {
            // 发送成功，把发送成功验证码放到redis里面
            // 设置有效时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        } else {
            return Result.error().message("短信发送失败");
        }
    }
}
