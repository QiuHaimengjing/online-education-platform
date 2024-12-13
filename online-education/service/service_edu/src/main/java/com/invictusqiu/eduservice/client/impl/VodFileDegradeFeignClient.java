package com.invictusqiu.eduservice.client.impl;

import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.client.VodClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 熔断器的实现类，出错时执行
 * @className: VodFileDegradeFeignClient.java
 * @author: qqdas
 * @createTime: 2023/10/10 21:52
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public Result removeAlyVideo(String id) {
        return Result.error().message("删除视频出错了");
    }

    @Override
    public Result deleteBatch(List<String> videoIdList) {
        return Result.error().message("删除多个视频出错了");
    }

}
