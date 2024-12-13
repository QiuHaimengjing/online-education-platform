package com.invictusqiu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 章节实体类
 * @className: ChapterVo.java
 * @author: qqdas
 * @createTime: 2023/10/8 11:15
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    // 表示小节
    private List<VideoVo> children = new ArrayList<>();
}
