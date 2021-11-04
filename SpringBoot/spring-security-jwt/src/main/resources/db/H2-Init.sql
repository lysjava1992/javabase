--删除
DROP TABLE  IF EXISTS user;
--建表
CREATE TABLE user(
  id identity(1,1) PRIMARY  key ,
  username varchar (50),
  password varchar (200),
  phone varchar (15),
  role  varchar (20)
);
INSERT INTO  user (username,password,phone,role)
            VALUES
            ('admin', '$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe' ,'15063379080','admin') ,
            ( 'king' , '$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe' ,'1506337xxxx', 'admin'),
            ('test','$2a$10$0Hza1cWyJtRwGM6smWnQDOal3AhO3wH3tLP32O8q9tFp642MTPwMe'  ,'1506337xxxx','user');