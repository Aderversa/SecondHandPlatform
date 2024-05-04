create database if not exists second_hand;

use second_hand;

SET NAMES 'utf8mb4';

create table user
(
    id int unsigned primary key auto_increment comment '用户ID',
    username varchar(20) not null comment '用户名',
    password varchar(50) not null comment '密码',
    nickname varchar(20) default '' comment '昵称',
    email varchar(128) default '' comment '邮箱',
    user_img varchar(128) default '' null comment '头像',
    update_time date not null comment '更新时间',
    create_time date not null comment '创建时间',
    power tinyint not null default 1 comment '权限'
)default character set utf8mb4 comment '用户表';

create table book_type
(
    id int unsigned primary key auto_increment comment '类别ID',
    name varchar(20) not null comment '类别名'
)default character set utf8mb4 comment '书籍类别';

create table book
(
    id int unsigned primary key auto_increment comment '书籍ID',
    name varchar(50) not null comment '书籍名称',
    type_id int unsigned not null comment '类别',
    price decimal(12, 2) not null comment '价格',
    isbn varchar(20) not null comment 'ISBN号',
    img varchar(128) null comment '头像',
    detail varchar(500) default '' null comment '书籍描述',
    release_time date not null comment '上架时间',
    seller_id int unsigned not null comment '卖家ID',
    seller_name varchar(20) not null comment '卖家名',
    purchased int not null comment '是否已被购买',
    constraint type foreign key (type_id) references book_type(id),
    constraint seller foreign key (seller_id) references user(id)
)default character set utf8mb4 comment '待售书籍';

create table `order`
(
    id int unsigned primary key auto_increment comment '订单ID',
    user_id int unsigned not null comment '用户ID',
    book_id int unsigned not null comment '书籍ID',
    pay_time date not null comment '下单时间',
    address varchar(100) not null comment '收货地址',
    status tinyint not null comment '订单状态',
    constraint buyer foreign key (user_id) references user(id),
    constraint want_book foreign key (book_id) references book(id)
)default character set utf8mb4 comment '订单';

insert into book_type (name) values ('全部');