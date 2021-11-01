--删除
DROP TABLE  IF EXISTS user;
--建表
CREATE TABLE user(
  id identity(1,1) PRIMARY  key ,
  username varchar (50),
  password varchar (200),
  role  varchar (20)
);
INSERT INTO  user (username,password,role)
            VALUES
            ('admin','$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe','admin'),
            ('king','$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe' ,'admin'),
            ('test','$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe' ,'user');