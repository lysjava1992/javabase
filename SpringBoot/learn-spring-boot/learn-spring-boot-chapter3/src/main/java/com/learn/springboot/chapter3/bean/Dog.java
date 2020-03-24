package com.learn.springboot.chapter3.bean;

import lombok.Data;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-23 11:04
 **/
@Data
public class Dog {
    private String name;
    private Integer age;

    @Override
    public String toString() {
        return "【" +
                "name='" + name + '\'' +
                ", age=" + age +
                '】';
    }
}
