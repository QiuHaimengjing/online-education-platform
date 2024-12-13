-- auto-generated definition
create table edu_chapter
(
    id           char(19)                     not null comment '章节ID'
        primary key,
    course_id    char(19)                     not null comment '课程ID',
    title        varchar(50)                  not null comment '章节名称',
    sort         int unsigned     default '0' not null comment '显示排序',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除，0（false）未删除'
)
    comment '课程章节表';

create index idx_course_id
    on edu_chapter (course_id);

