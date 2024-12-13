-- auto-generated definition
create table acl_permission
(
    id               char(19)         default ''  not null comment '编号'
        primary key,
    pid              char(19)         default ''  not null comment '所属上级',
    name             varchar(20)      default ''  not null comment '名称',
    type             tinyint          default 0   not null comment '类型(1:菜单,2:按钮)',
    permission_value varchar(50)                  null comment '权限值',
    path             varchar(100)                 null comment '访问路径',
    component        varchar(100)                 null comment '组件路径',
    icon             varchar(50)                  null comment '图标',
    status           tinyint                      null comment '状态(0:禁止,1:正常)',
    is_deleted       tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create       datetime                     null comment '创建时间',
    gmt_modified     datetime                     null comment '更新时间',
    redirect         varchar(100)                 null comment '重定向'
)
    comment '权限';

create index idx_pid
    on acl_permission (pid);

