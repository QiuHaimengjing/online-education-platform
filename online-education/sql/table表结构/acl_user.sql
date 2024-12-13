-- auto-generated definition
create table acl_user
(
    id           char(19)                     not null comment '会员id'
        primary key,
    username     varchar(20)      default ''  not null comment '微信openid',
    password     varchar(32)      default ''  not null comment '密码',
    nick_name    varchar(50)                  null comment '昵称',
    salt         varchar(255)                 null comment '用户头像',
    token        varchar(100)                 null comment '用户签名',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间',
    constraint uk_username
        unique (username)
)
    comment '用户表';

