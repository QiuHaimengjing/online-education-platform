package com.invictusqiu.eduservice.mapper;

import com.invictusqiu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.invictusqiu.eduservice.entity.frontvo.CourseWebVo;
import com.invictusqiu.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程基本信息 Mapper 接口
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    // 根据课程id查询课程确认信息的Mapper接口
    public CoursePublishVo getPublishCourseInfo(String courseId);

    // 根据课程id，编写sql语句查询课程信息
    CourseWebVo getBaseCourseInfo(String courseId);
}
