package com.invictusqiu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

import java.util.List;

/**
 * @description: 测试视频类，根据视频id获取视频播放地址
 * @className: TestVod.java
 * @author: qqdas
 * @createTime: 2023/10/9 14:54
 */
public class TestVod {
    public static void main(String[] args) {
        String accessKeyId = "";
        String accessKeySecret = "";

        String title = "6 - How Does Project Submission Work - upload by sdk";  // 上传之后文件名称
        String fileName = "E:\\6 - What If I Want to Move Faster.mp4";   // 本地文件路径和名称

        // 上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    // 根据视频id获取视频播放凭证
    public static void getPlayAuth() throws Exception {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("","");
        // 创建获取视频凭证request和response
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // 向request设置视频id
        request.setVideoId("bc972cc0666871eebd7d5017f0f80102");
        // 调用初始化对象的方法得到凭证
        response = client.getAcsResponse(request);
        System.out.println("playauth: " + response.getPlayAuth());
    }

    // 根据视频id获取视频播放地址
    public static void getPlayUrl() throws Exception {
        // 创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("","");

        // 创建获取视频地址request和response
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();

        // 向request对象里面设置视频id
        request.setVideoId("bc972cc0666871eebd7d5017f0f80102");

        // 调用初始化对象里面的方法，传递request，获取数据
        response = client.getAcsResponse(request);

        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        // 播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.println("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        // Base信息
        System.out.println("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }
}
