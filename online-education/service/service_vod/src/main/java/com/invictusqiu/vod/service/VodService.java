package com.invictusqiu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @description: 视频点播服务器接口
 * @className: VodService.java
 * @author: qqdas
 * @createTime: 2023/10/9 16:29
 */
public interface VodService {
    // 上传视频到阿里云
    String uploadVideoAly(MultipartFile file);

    // 删除多个阿里云视频的方法
    void removeMoreAlyVideo(List<String> videoIdList);
}
