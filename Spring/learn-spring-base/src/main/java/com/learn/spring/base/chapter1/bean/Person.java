package com.learn.spring.base.chapter1.bean;

import java.util.List;
import java.util.Map;

public class Person {
    private int age;
    private String name;
    private List<String> cours;
    private Map<String,String> reportCard;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCours() {
        return cours;
    }

    public void setCours(List<String> cours) {
        this.cours = cours;
    }

    public Map<String, String> getReportCard() {
        return reportCard;
    }

    public void setReportCard(Map<String, String> reportCard) {
        this.reportCard = reportCard;
    }

    @Override
    public String toString() {
        return "{" +
                "age=" + age +
                ", name='" + name +"'"+
                '}';
    }
}
