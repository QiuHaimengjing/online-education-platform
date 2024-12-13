package com.invictusqiu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.eduservice.entity.EduCourse;
import com.invictusqiu.eduservice.entity.EduCourseDescription;
import com.invictusqiu.eduservice.entity.EduTeacher;
import com.invictusqiu.eduservice.entity.frontvo.CourseFrontVo;
import com.invictusqiu.eduservice.entity.frontvo.CourseWebVo;
import com.invictusqiu.eduservice.entity.vo.CourseInfoVo;
import com.invictusqiu.eduservice.entity.vo.CoursePublishVo;
import com.invictusqiu.eduservice.mapper.EduCourseMapper;
import com.invictusqiu.eduservice.service.EduChapterService;
import com.invictusqiu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.invictusqiu.eduservice.service.EduVideoService;
import com.invictusqiu.servicebase.exceptionhandler.EduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程基本信息 服务实现类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    // 课程描述注入
    @Autowired
    private EduCourseDescriptionServiceImpl courseDescriptionService;

    // 注入小节
    @Autowired
    private EduVideoService eduVideoService;

    // 注入章节
    @Autowired
    private EduChapterService chapterService;

    // 添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 1.向课程表添加课程基本信息
        // CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            // 添加失败
            throw new EduException(20001,"添加课程信息失败");
        }

        // 获取添加之后课程id
        String cid = eduCourse.getId();

        // 2.向课程简介表添加课程简介
        // edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        // 设置描述id就是课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);

        return cid;
    }

    // 根据课程id查询课程基本信息（数据回显）
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {

        // 1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);

        // 2.查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    // 修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 1.修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new EduException(20001,"修改课程信息失败");
        }

        // 2.修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    // 根据课程id查询课程确认信息
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        // 调用mapper
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    // 删除课程
    @Override
    public void removeCourse(String courseId) {
        // 1.根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        // 2.根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        // 3.根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        // 4.根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {  //失败返回
            throw new EduException(20001,"删除失败");
        }

    }

    // 1.条件查询带分页查询课程
    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo) {

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 判断条件值是否为空，不为空拼接
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectParentId())) { //一级分类
            wrapper.eq("subject_parent_id", courseFrontVo.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getSubjectId())) {   //二级分类
            wrapper.eq("subject_id",courseFrontVo.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseFrontVo.getBuyCountSort())) {   //关注度
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getGmtCreateSort())) {   //最新
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseFrontVo.getPriceSort())) {   //价格
            wrapper.orderByDesc("price");
        }
        // 只获取发布状态的课程
        wrapper.eq("status","Normal");
        baseMapper.selectPage(pageCourse,wrapper);

        List<EduCourse> records = pageCourse.getRecords();
        long current = pageCourse.getCurrent();
        long pages = pageCourse.getPages();
        long size = pageCourse.getSize();
        long total = pageCourse.getTotal();
        boolean hasNext = pageCourse.hasNext();
        boolean hasPrevious = pageCourse.hasPrevious();

        // 把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        // map返回
        return map;
    }

    // 根据课程id，编写sql语句查询课程信息
    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
