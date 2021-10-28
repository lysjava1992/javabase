--	h2-schame.sql
drop table if exists users;
-- 创建表
create table users
(
    id identity(1,1),
    username varchar(20),
    password varchar(200),
    role     varchar(20),
    PRIMARY KEY (`id`)
);
-- 插入表数据 h2-data.sql
insert into users(username, password,role)
values ('LiSi', '354a71635bee24473dd0011d351487eb', 'user');
insert into users(username, password,role)
values ('king', '6f52c8129e2203d30f9bf5ab81a2f3a2', 'admin');
insert into users(username, password,role)
values ('admin', '928bfd2577490322a6e19b793691467e', 'admin');


drop table if exists permission;
-- 创建表
create table permission
(
    id identity(1,1),
    identify  varchar(20),
    PRIMARY KEY (`id`)
);
-- 插入表数据 h2-data.sql
insert into permission(identify)values ('user:add');
insert into permission(identify)values ('user:delete');
insert into permission(identify)values ('user:update');
insert into permission(identify)values ('user:find');


drop table if exists role_permission;
create table role_permission
(
    role     varchar(20),
    permissionId  int
);
insert into role_permission(role,permissionId)values ('admin',1);
insert into role_permission(role,permissionId)values ('admin',2);
insert into role_permission(role,permissionId)values ('admin',3);
insert into role_permission(role,permissionId)values ('admin',4);
insert into role_permission(role,permissionId)values ('user',3);
insert into role_permission(role,permissionId)values ('user',4);


