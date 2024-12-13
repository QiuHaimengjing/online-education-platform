package com.invictusqiu.eduservice.client;

import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.client.impl.VodFileDegradeFeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description: 服务调用的调用端接口
 * @description: 注意路径要写全，@PathVariable注解必须指明参数名称，否则出错
 * @className: VodClient.java
 * @author: qqdas
 * @createTime: 2023/10/10 16:52
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class) // 指定从service-vod服务中调用功能，出错调用实现类
@Component
public interface VodClient {

    // 定义调用的方法路径
    // 根据视频id删除阿里云视频
    @DeleteMapping("/eduvod/video/removeAlyVideo/{id}")
    public Result removeAlyVideo(@PathVariable("id") String id);

    // 定义调用删除多个视频的方法
    // 删除多个阿里云视频的方法
    // 参数：多个视频id List videoIdList
    @DeleteMapping("/eduvod/video/delete-batch")
    public Result deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
