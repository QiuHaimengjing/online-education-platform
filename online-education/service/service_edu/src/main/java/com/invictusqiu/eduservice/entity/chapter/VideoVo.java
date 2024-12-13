package com.invictusqiu.eduservice.entity.chapter;

import lombok.Data;

/**
 * @description: 小节实体类
 * @className: VideoVo.java
 * @author: qqdas
 * @createTime: 2023/10/8 11:15
 */
@Data
public class VideoVo {

    private String id;

    private String title;

    private Integer isFree;

    private String videoSourceId;   //视频id
}
