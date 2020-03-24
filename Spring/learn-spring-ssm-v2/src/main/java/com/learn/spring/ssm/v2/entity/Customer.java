package com.learn.spring.ssm.v2.entity;

import lombok.Data;


/**
 *
 * @ClassName Costomer
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/19 10:01
 * @Version 1.0
 **/
@Data
public class Customer {
    private Long id;
    private String username;
    private String password;
    private String createTime;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
