-- auto-generated definition
create table edu_video
(
    id                  char(19)                         not null comment '视频ID'
        primary key,
    course_id           char(19)                         not null comment '课程ID',
    chapter_id          char(19)                         not null comment '章节ID',
    title               varchar(50)                      not null comment '节点名称',
    video_source_id     varchar(100)                     null comment '云端视频资源',
    video_original_name varchar(100)                     null comment '原始文件名称',
    sort                int unsigned     default '0'     not null comment '排序字段',
    play_count          bigint unsigned  default '0'     not null comment '播放次数',
    is_free             tinyint unsigned default '0'     not null comment '是否可以试听：0收费 1免费',
    duration            float            default 0       not null comment '视频时长（秒）',
    status              varchar(20)      default 'Empty' not null comment 'Empty未上传 Transcoding转码中  Normal正常',
    size                bigint unsigned  default '0'     not null comment '视频源文件大小（字节）',
    version             bigint unsigned  default '1'     not null comment '乐观锁',
    gmt_create          datetime                         not null comment '创建时间',
    gmt_modified        datetime                         not null comment '更新时间',
    is_deleted          tinyint unsigned default '0'     not null
)
    comment '课程小节表';

create index idx_chapter_id
    on edu_video (chapter_id);

create index idx_course_id
    on edu_video (course_id);

