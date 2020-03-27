package com.learn.springboot.chapter6.entity;

import lombok.Data;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:15
 **/
@Data
public class Customer {
    private Long id;
    private String username;
    private String password;
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
