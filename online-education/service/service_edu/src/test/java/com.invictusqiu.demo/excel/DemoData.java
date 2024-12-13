package com.invictusqiu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description: excel测试实体类
 * @className: DemoData.java
 * @author: qqdas
 * @createTime: 2023/10/6 16:30
 */
@Data
public class DemoData {
    // 设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
