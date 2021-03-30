package com.learn.spring.base.chapter14;

public class DerivedTestBean {
    private String name;
    private int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "DerivedTestBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
