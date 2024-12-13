package com.invictusqiu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 接收条件查询的数据，将其封装成为对象（条件查询对象）
 * @className: TeacherQuery.java
 * @author: qqdas
 * @createTime: 2023/10/1 13:08
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "教师名称，模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2023-10-01 10:01:10")
    private String begin; // 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "2023-12-25 12:25:10")
    private String end;
}
