package com.learn.drools.model;

import lombok.Data;

@Data
public class User {
    private  int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

}
