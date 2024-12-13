package com.invictusqiu.eduservice.controller;


import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.client.VodClient;
import com.invictusqiu.eduservice.entity.EduVideo;
import com.invictusqiu.eduservice.service.EduVideoService;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程小节表 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
@Api(tags = "小节管理")
@RestController
@RequestMapping("/eduservice/video")
//@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService videoService;

    // 注入vodClient
    @Autowired
    private VodClient vodClient;

    // 添加小节
    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public Result addVideo(
            @ApiParam(name = "eduVideo", value = "小节信息", required = true)
            @RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return Result.ok();
    }

    // 删除小节，删除对应阿里云视频
    @ApiOperation(value = "删除小节")
    @DeleteMapping("{id}")
    public Result deleteVideo(
            @ApiParam(name = "id", value = "小节id", required = true)
            @PathVariable String id) {
        // 根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        // 判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)) {
            // 根据视频id，远程调用实现视频删除
            Result result = vodClient.removeAlyVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new EduException(20001,"删除视频失败，熔断器...");
            }
        }
        // 删除小节
        videoService.removeById(id);
        return Result.ok();
    }

    // 根据小节id查询
    @ApiOperation(value = "根据小节id查询")
    @GetMapping("getVideoInfo/{id}")
    public Result getVideoById(
            @ApiParam(name = "id", value = "小节id", required = true)
            @PathVariable String id) {
        EduVideo eduVideo = videoService.getById(id);
        return Result.ok().data("video",eduVideo);
    }

    // 修改小节
    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public Result updateVideo(
            @ApiParam(name = "eduVideo", value = "小节信息", required = true)
            @RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return Result.ok();
    }

}

