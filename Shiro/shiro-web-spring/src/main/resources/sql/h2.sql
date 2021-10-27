--	h2-schame.sql
drop table if exists users ;
-- 创建表
create table users(
  id int,username varchar(20),password varchar(20)

);
-- 插入表数据 h2-data.sql
insert into users(id,username,password) values(1,'张老师','123456');
insert into users(id,username,password) values(2,'李老师','123456');