package com.invictusqiu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.entity.EduCourse;
import com.invictusqiu.eduservice.entity.vo.CourseInfoVo;
import com.invictusqiu.eduservice.entity.vo.CoursePublishVo;
import com.invictusqiu.eduservice.entity.vo.CourseQuery;
import com.invictusqiu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程基本信息 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
//@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService courseService;

    // 条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public Result pageCourseCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,

            @ApiParam(name = "courseQuery", value = "查询条件")
            @RequestBody(required = false) CourseQuery courseQuery) {
        // 创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);

        // 构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 动态sql
        String title = courseQuery.getTitle();
        String status = courseQuery.getStatus();
        String begin = courseQuery.getBegin();
        String end = courseQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(title)){
            // 构建条件
            wrapper.like("title",title);
        }
        if (!StringUtils.isEmpty(status)){
            wrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        // 排序
        wrapper.orderByDesc("gmt_create");

        // 调用方法实现条件查询分页
        courseService.page(pageCourse,wrapper);

        long total = pageCourse.getTotal(); // 总记录数
        List<EduCourse> records = pageCourse.getRecords(); // 数据list集合
        return Result.ok().data("total",total).data("rows",records);
    }

    // 添加课程基本信息的方法
    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourseInfo")
    public Result addCourseInfo(
            @ApiParam(name = "courseInfoVo", value = "课程信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        // 返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return Result.ok().data("courseId",id);
    }

    // 根据课程id查询课程基本信息（数据回显）
    @ApiOperation(value = "根据课程id查询课程基本信息")
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo",courseInfoVo);
    }

    // 修改课程信息
    @ApiOperation(value = "修改课程信息")
    @PostMapping("updateCourseInfo")
    public Result updateCourseInfo(
            @ApiParam(name = "courseInfoVo", value = "课程信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return Result.ok();
    }

    // 根据课程id查询课程确认信息
    @ApiOperation(value = "根据课程id查询课程确认信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public Result getPublishCourseInfo(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return Result.ok().data("publishCourse",coursePublishVo);
    }

    // 课程最终发布
    // 修改课程状态
    @ApiOperation(value = "发布课程，修改课程状态")
    @PostMapping("publishCourse/{id}")
    public Result publishCourse(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal"); // 设置课程发布状态
        courseService.updateById(eduCourse);
        return Result.ok();
    }

    // 删除课程
    @ApiOperation(value = "删除课程")
    @DeleteMapping("{courseId}")
    public Result deleteCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Result.ok();
    }

}

