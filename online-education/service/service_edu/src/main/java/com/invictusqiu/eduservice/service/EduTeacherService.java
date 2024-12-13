package com.invictusqiu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.invictusqiu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-09-30
 */
public interface EduTeacherService extends IService<EduTeacher> {
    // 1.分页查询讲师的方法

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
