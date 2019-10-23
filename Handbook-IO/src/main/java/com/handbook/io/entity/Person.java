package com.handbook.io.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-23 16:17
 **/
@Data
public class Person implements Serializable{
    private static final long serialVersionUID = -5649996539738943994L;
    private int age;
    private double height;
    private double weight;
    private String name;
    private String addr;

    public Person() {
    }

    public Person(int age, double height, double weight, String name, String addr) {
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.addr = addr;
    }
}
