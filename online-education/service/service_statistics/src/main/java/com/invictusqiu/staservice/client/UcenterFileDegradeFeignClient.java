package com.invictusqiu.staservice.client;

import com.invictusqiu.commonutils.Result;
import org.springframework.stereotype.Component;

/**
 * @description: 熔断器实现类，出错时执行
 * @className: UcenterFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/23 10:37
 */
@Component
public class UcenterFileDegradeFeignClient implements UcenterClient {
    @Override
    public Result countRegister(String day) {
        return Result.error().message("获取数据时出错了");
    }
}
