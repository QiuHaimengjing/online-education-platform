-- auto-generated definition
create table statistics_daily
(
    id              char(19)      not null comment '主键'
        primary key,
    date_calculated varchar(20)   not null comment '统计日期',
    register_num    int default 0 not null comment '注册人数',
    login_num       int default 0 not null comment '登录人数',
    video_view_num  int default 0 not null comment '每日播放视频数',
    course_num      int default 0 not null comment '每日新增课程数',
    gmt_create      datetime      not null comment '创建时间',
    gmt_modified    datetime      not null comment '更新时间'
)
    comment '网站统计日数据' charset = utf8mb3;

create index statistics_day
    on statistics_daily (date_calculated);

