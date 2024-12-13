package com.invictusqiu.eduservice.service;

import com.invictusqiu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.invictusqiu.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程章节表 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
public interface EduChapterService extends IService<EduChapter> {

    // 课程大纲列表，根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    // 删除的方法
    boolean deleteChapter(String chapterId);

    // 2.根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
