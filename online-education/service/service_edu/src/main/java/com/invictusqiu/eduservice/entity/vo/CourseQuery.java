package com.invictusqiu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 接收课程条件查询的数据，将其封装成为对象（条件查询对象）
 * @className: CourseQuery.java
 * @author: qqdas
 * @createTime: 2023/10/9 11:07
 */
@ApiModel(value = "Course查询条件对象", description = "查询条件列表")
@Data
public class CourseQuery {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "查询开始时间", example = "2023-10-01 10:01:10")
    private String begin;

    @ApiModelProperty(value = "查询结束时间", example = "2023-12-25 12:25:10")
    private String end;
}
