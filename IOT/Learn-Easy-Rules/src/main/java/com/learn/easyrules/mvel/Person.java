package com.learn.easyrules.mvel;

import lombok.Data;

@Data
public class Person {
    private String name;
    private int age;
    private boolean adult;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
