package com.learn.spring.base.chapter23;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter23 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter23.xml");
        Simple simple=context.getBean(Simple.class);
        System.out.println(simple.getRandom());
        System.out.println(simple.getDefaultLocale());
    }
}
