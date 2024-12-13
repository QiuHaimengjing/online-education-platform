-- auto-generated definition
create table crm_banner
(
    id           char(19)         default ''  not null comment 'ID'
        primary key,
    title        varchar(20)      default ''  null comment '标题',
    image_url    varchar(500)     default ''  not null comment '图片地址',
    link_url     varchar(500)     default ''  null comment '链接地址',
    sort         int unsigned     default '0' not null comment '排序',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间'
)
    comment '首页banner表';

