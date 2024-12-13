-- auto-generated definition
create table acl_user_role
(
    id           char(19)         default ''  not null comment '主键id'
        primary key,
    role_id      char(19)         default '0' not null comment '角色id',
    user_id      char(19)         default '0' not null comment '用户id',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间'
)
    charset = utf8mb3;

create index idx_role_id
    on acl_user_role (role_id);

create index idx_user_id
    on acl_user_role (user_id);

