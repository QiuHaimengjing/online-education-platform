-- auto-generated definition
create table edu_course
(
    id                char(19)                                not null comment '课程ID'
        primary key,
    teacher_id        char(19)                                not null comment '课程讲师ID',
    subject_id        char(19)                                not null comment '课程专业ID',
    subject_parent_id char(19)                                null comment '课程专业父级ID',
    title             varchar(50)                             not null comment '课程标题',
    price             decimal(10, 2) unsigned default 0.00    not null comment '课程销售价格，设置为0则可免费观看',
    lesson_num        int unsigned            default '0'     not null comment '总课时',
    cover             varchar(255) charset utf8mb3            not null comment '课程封面图片路径',
    buy_count         bigint unsigned         default '0'     not null comment '销售数量',
    view_count        bigint unsigned         default '0'     not null comment '浏览数量',
    version           bigint unsigned         default '1'     not null comment '乐观锁',
    status            varchar(10)             default 'Draft' not null comment '课程状态 Draft未发布  Normal已发布',
    is_deleted        tinyint unsigned        default '0'     not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create        datetime                                not null comment '创建时间',
    gmt_modified      datetime                                not null comment '更新时间'
)
    comment '课程基本信息';

create index idx_subject_id
    on edu_course (subject_id);

create index idx_teacher_id
    on edu_course (teacher_id);

create index idx_title
    on edu_course (title);

