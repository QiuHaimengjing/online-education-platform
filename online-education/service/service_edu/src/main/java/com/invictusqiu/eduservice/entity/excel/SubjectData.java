package com.invictusqiu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description: 读取excel，课程分类实体类
 * @className: SubjectData.java
 * @author: qqdas
 * @createTime: 2023/10/6 17:44
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubjectName;

    @ExcelProperty(index = 1)
    private String twoSubjectName;


}
