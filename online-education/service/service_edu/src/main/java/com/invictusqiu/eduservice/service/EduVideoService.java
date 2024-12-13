package com.invictusqiu.eduservice.service;

import com.invictusqiu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程小节表 服务类
 * </p>
 *
 * @author qqdas
 * @since 2023-10-07
 */
public interface EduVideoService extends IService<EduVideo> {

    // 1.根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
