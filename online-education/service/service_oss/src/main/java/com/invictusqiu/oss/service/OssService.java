package com.invictusqiu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 文件上传服务类
 * @className: OssService.java
 * @author: qqdas
 * @createTime: 2023/10/6 10:39
 */
public interface OssService {
    // 上传头像到OSS
    String uploadFileAvatar(MultipartFile file);
}
