-- auto-generated definition
create table edu_comment
(
    id           char(19)                     not null comment '讲师ID'
        primary key,
    course_id    varchar(19)      default ''  not null comment '课程id',
    teacher_id   char(19)         default ''  not null comment '讲师id',
    member_id    varchar(19)      default ''  not null comment '会员id',
    nickname     varchar(50)                  null comment '会员昵称',
    avatar       varchar(255)                 null comment '会员头像',
    content      varchar(500)                 null comment '评论内容',
    is_deleted   tinyint unsigned default '0' not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    gmt_create   datetime                     not null comment '创建时间',
    gmt_modified datetime                     not null comment '更新时间'
)
    comment '评论';

create index idx_course_id
    on edu_comment (course_id);

create index idx_member_id
    on edu_comment (member_id);

create index idx_teacher_id
    on edu_comment (teacher_id);

