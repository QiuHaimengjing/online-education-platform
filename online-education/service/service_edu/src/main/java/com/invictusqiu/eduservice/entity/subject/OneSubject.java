package com.invictusqiu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 一级分类实体类
 * @className: OneSubject.java
 * @author: qqdas
 * @createTime: 2023/10/7 11:47
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    // 一个一级分类有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
