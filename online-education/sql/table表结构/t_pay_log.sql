-- auto-generated definition
create table t_pay_log
(
    id             char(19)         default ''   not null
        primary key,
    order_no       varchar(20)      default ''   not null comment '订单号',
    pay_time       datetime                      null comment '支付完成时间',
    total_fee      decimal(10, 2)   default 0.01 null comment '支付金额（分）',
    transaction_id varchar(30)                   null comment '交易流水号',
    trade_state    char(20)                      null comment '交易状态',
    pay_type       tinyint          default 0    not null comment '支付类型（1：微信 2：支付宝）',
    attr           text                          null comment '其他属性',
    is_deleted     tinyint unsigned default '0'  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create     datetime                      not null comment '创建时间',
    gmt_modified   datetime                      not null comment '更新时间',
    constraint uk_order_no
        unique (order_no)
)
    comment '支付日志表';

