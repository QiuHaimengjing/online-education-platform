package com.invictusqiu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.invictusqiu.oss.service.OssService;
import com.invictusqiu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @description: 文件上传服务实现类
 * @className: OssServiceImpl.java
 * @author: qqdas
 * @createTime: 2023/10/6 10:40
 */
@Service
public class OssServiceImpl implements OssService {
    // 上传头像到OSS
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        try {
            // 创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();

            // 1.在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            // 文件名例如：yuy76t5rew01.jpg
            fileName = uuid+fileName;

            // 2.把文件按照日期进行分类
            // 获取当前日期
            // 2023/10/06
            String datePath = new DateTime().toString("yyyy/MM/dd");

            // 拼接
            // 2023/10/06/avatar232.jpg
            fileName = datePath+"/"+fileName;

            // 调用OSS方法实现上传
            // 第一个参数 Bucket名称
            // 第二个参数 上传到OSS文件路径和文件名称 aa/bb/1.jpg
            // 第三个参数 上传文件输入流
            ossClient.putObject(bucketName,fileName,inputStream);

            // 关闭OSSClient
            ossClient.shutdown();;

            // 把上传之后文件路径返回
            // 需要把上传到阿里云OSS路径手动拼接出来
            // https://invictusqiu.oss-cn-beijing.aliyuncs.com/29943c96-3334-4a57-9d81-c9b1aa00279a.jpg
            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
