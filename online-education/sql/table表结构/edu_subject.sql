-- auto-generated definition
create table edu_subject
(
    id           char(19)                 not null comment '课程类别ID'
        primary key,
    title        varchar(10)              not null comment '类别名称',
    parent_id    char(19)     default '0' not null comment '父ID',
    sort         int unsigned default '0' not null comment '排序字段',
    gmt_create   datetime                 not null comment '创建时间',
    gmt_modified datetime                 not null comment '更新时间'
)
    comment '课程分类表';

create index idx_parent_id
    on edu_subject (parent_id);

