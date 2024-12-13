-- auto-generated definition
create table ucenter_member
(
    id           char(19)               not null comment '会员id'
        primary key,
    openid       varchar(128)           null comment '微信openid',
    mobile       varchar(11) default '' null comment '手机号',
    password     varchar(255)           null comment '密码',
    nickname     varchar(50)            null comment '昵称',
    sex          tinyint unsigned       null comment '性别 1 女，2 男',
    age          tinyint unsigned       null comment '年龄',
    avatar       varchar(255)           null comment '用户头像',
    sign         varchar(100)           null comment '用户签名',
    is_disabled  tinyint(1)  default 0  not null comment '是否禁用 1（true）已禁用，  0（false）未禁用',
    is_deleted   tinyint(1)  default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime               not null comment '创建时间',
    gmt_modified datetime               not null comment '更新时间'
)
    comment '会员表';

