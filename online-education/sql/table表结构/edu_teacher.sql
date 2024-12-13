-- auto-generated definition
create table edu_teacher
(
    id           char(19)                     not null comment '讲师ID'
        primary key,
    name         varchar(20)                  not null comment '讲师姓名',
    intro        varchar(500)     default ''  not null comment '讲师简介',
    career       varchar(500)                 null comment '讲师资历，一句话说明讲师',
    level        int unsigned                 not null comment '头衔 1高级讲师 2首席讲师',
    avatar       varchar(255)                 null comment '讲师头像',
    sort         int unsigned     default '0' not null comment '排序',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除，0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间',
    constraint uk_name
        unique (name)
)
    comment '讲师';

