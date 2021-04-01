package com.learn.spring.base.chapter21;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter21 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter21.xml");
        DependsOnExoticType depends= (DependsOnExoticType) context.getBean("sample");
        System.out.println(depends.getTypeString());
    }
}
