-- auto-generated definition
create table acl_role_permission
(
    id            char(19)         default ''  not null
        primary key,
    role_id       char(19)         default ''  not null,
    permission_id char(19)         default ''  not null,
    is_deleted    tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create    datetime                     not null comment '创建时间',
    gmt_modified  datetime                     not null comment '更新时间'
)
    comment '角色权限' charset = utf8mb3;

create index idx_permission_id
    on acl_role_permission (permission_id);

create index idx_role_id
    on acl_role_permission (role_id);

