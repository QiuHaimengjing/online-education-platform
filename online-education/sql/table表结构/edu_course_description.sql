-- auto-generated definition
create table edu_course_description
(
    id           char(19)                     not null comment '课程ID'
        primary key,
    description  longtext                     null comment '课程简介',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间',
    is_deleted   tinyint unsigned default '0' not null
)
    comment '课程简介';

