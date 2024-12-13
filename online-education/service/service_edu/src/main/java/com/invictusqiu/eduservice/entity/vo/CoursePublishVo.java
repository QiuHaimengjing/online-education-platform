package com.invictusqiu.eduservice.entity.vo;

import lombok.Data;

/**
 * @description: 最终发布的课程确认信息数据实体类
 * @className: CoursePublishVo.java
 * @author: qqdas
 * @createTime: 2023/10/8 20:26
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
