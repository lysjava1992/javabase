package com.learn.springboot.chapter8.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:15
 * @Entity 指定对应的表；表示是一个实体类
 **/
@Entity(name = "user")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @Column(name = "create_time")
    private String createTime;
    private String addr;

    public Customer() {
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
