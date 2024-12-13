package com.invictusqiu.eduservice.service;

import com.invictusqiu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.invictusqiu.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-06
 */
public interface EduSubjectService extends IService<EduSubject> {

    // 添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    // // 课程分类列表（树形）
    List<OneSubject> getAllOneTwoSubject();
}
