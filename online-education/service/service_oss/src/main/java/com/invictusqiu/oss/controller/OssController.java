package com.invictusqiu.oss.controller;

import com.invictusqiu.commonutils.Result;
import com.invictusqiu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 上传文件控制器
 * @className: OssController.java
 * @author: qqdas
 * @createTime: 2023/10/6 10:38
 */
@RestController
@RequestMapping("/eduoss/fileoss")
//@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    // 上传头像的方法
    @PostMapping
    public Result uploadOssFile(MultipartFile file) {
        // 获取上传文件 MultipartFile
        // 返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
