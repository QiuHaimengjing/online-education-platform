package com.invictusqiu.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.entity.EduCourse;
import com.invictusqiu.eduservice.entity.EduTeacher;
import com.invictusqiu.eduservice.service.EduCourseService;
import com.invictusqiu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 前台讲师控制器
 * @className: TeacherFrontController.java
 * @author: qqdas
 * @createTime: 2023/10/14 10:09
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    // 1.分页查询讲师的方法
    @GetMapping("getTeacherFrontList/{current}/{limit}")
    public Result getTeacherFrontList(@PathVariable long current, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher);
        // 返回分页所有数据
        return Result.ok().data(map);
    }

    // 2.讲师详情的功能
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public Result getTeacherFrontInfo(@PathVariable String teacherId) {
        // 1.根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);

        // 2.根据讲师id查询所有课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        // 3.查询发布的课程
        wrapper.eq("status", "Normal");
        List<EduCourse> courseList = courseService.list(wrapper);

        return Result.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}
