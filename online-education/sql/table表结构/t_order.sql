-- auto-generated definition
create table t_order
(
    id           char(19)         default ''   not null
        primary key,
    order_no     varchar(20)      default ''   not null comment '订单号',
    course_id    varchar(19)      default ''   not null comment '课程id',
    course_title varchar(100)                  null comment '课程名称',
    course_cover varchar(255)                  null comment '课程封面',
    teacher_name varchar(20)                   null comment '讲师名称',
    member_id    varchar(19)      default ''   not null comment '会员id',
    nickname     varchar(50)                   null comment '会员昵称',
    mobile       varchar(11)                   null comment '会员手机',
    total_fee    decimal(10, 2)   default 0.01 null comment '订单金额（分）',
    pay_type     tinyint                       null comment '支付类型（1：微信 2：支付宝）',
    status       tinyint                       null comment '订单状态（0：未支付 1：已支付）',
    is_deleted   tinyint unsigned default '0'  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                      not null comment '创建时间',
    gmt_modified datetime                      not null comment '更新时间',
    constraint ux_order_no
        unique (order_no)
)
    comment '订单';

create index idx_course_id
    on t_order (course_id);

create index idx_member_id
    on t_order (member_id);

