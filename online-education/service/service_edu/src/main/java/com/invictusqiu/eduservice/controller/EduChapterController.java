package com.invictusqiu.eduservice.controller;


import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.entity.EduChapter;
import com.invictusqiu.eduservice.entity.chapter.ChapterVo;
import com.invictusqiu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程章节表 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
@Api(tags = "章节管理")
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    // 课程大纲列表，根据课程id进行查询
    @ApiOperation(value = "根据课程id进行查询")
    @GetMapping("getChapterVideo/{courseId}")
    public Result getChapterVideo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("allChapterVideo",list);
    }

    // 添加章节
    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public Result addChapter(
            @ApiParam(name = "eduChapter", value = "章节信息", required = true)
            @RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return Result.ok();
    }

    // 根据章节id查询
    @ApiOperation(value = "根据章节id查询")
    @GetMapping("getChapterInfo/{chapterId}")
    public Result getChapterInfo(
            @ApiParam(name = "chapterId", value = "章节id", required = true)
            @PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return Result.ok().data("chapter",eduChapter);
    }

    // 修改章节
    @ApiOperation(value = "修改章节")
    @PostMapping("updateChapter")
    public Result updateChapter(
            @ApiParam(name = "eduChapter", value = "章节信息", required = true)
            @RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return Result.ok();
    }

    // 删除的方法
    @ApiOperation(value = "删除章节")
    @DeleteMapping("{chapterId}")
    public Result deleteChapter(
            @ApiParam(name = "chapterId", value = "章节id", required = true)
            @PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

