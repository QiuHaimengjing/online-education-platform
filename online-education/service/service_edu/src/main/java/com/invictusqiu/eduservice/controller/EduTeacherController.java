package com.invictusqiu.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.commonutils.Result;
import com.invictusqiu.eduservice.entity.EduTeacher;
import com.invictusqiu.eduservice.entity.vo.TeacherQuery;
import com.invictusqiu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author qqdas
 * @since 2023-09-30
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
//@CrossOrigin //解决跨域
public class EduTeacherController {

    // 访问地址：http://localhost:8080/eduservice/teacher/findAll
    // 把service注入
    @Autowired
    EduTeacherService teacherService;

    //1 查询讲师表所有数据
    // rest风格
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return Result.ok().data("items",list);
    }

    //2 逻辑删除讲师的方法
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public Result removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag){
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //3 分页查询讲师的方法
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        // 调用方法实现分页
        // 调用方法时候，底层封装，把分页所有数据装到pageTeacher对象里面
        teacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return Result.ok().data("total",total).data("rows",records);
    }

    //4 条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页讲师列表")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页码", required = true)
            @PathVariable long current,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,

            @ApiParam(name = "teacherQuery", value = "查询条件")
            @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis 动态sql
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // 判断条件值是否为空，如果不为空拼接条件
        if (!StringUtils.isEmpty(name)){
            // 构建条件
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
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
        teacherService.page(pageTeacher,wrapper);

        long total = pageTeacher.getTotal(); //总记录数
        List<EduTeacher> records = pageTeacher.getRecords(); //数据list集合
        return Result.ok().data("total",total).data("rows",records);
    }

    //5 添加讲师接口的方法
    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(
            @ApiParam(name = "eduTeacher", value = "讲师信息", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean save = teacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    //6 根据讲师id进行查询接口的方法（数据回显）
    @ApiOperation(value = "根据讲师ID进行查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return Result.ok().data("teacher",eduTeacher);
    }

    //7 讲师修改功能接口的方法
    @ApiOperation(value = "修改讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(
            @ApiParam(name = "eduTeacher", value = "讲师信息", required = true)
            @RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}

