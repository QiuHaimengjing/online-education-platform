-- auto-generated definition
create table acl_role
(
    id           char(19)         default ''  not null comment '角色id'
        primary key,
    role_name    varchar(20)      default ''  not null comment '角色名称',
    role_code    varchar(20)                  null comment '角色编码',
    remark       varchar(255)                 null comment '备注',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间'
)
    charset = utf8mb3;

