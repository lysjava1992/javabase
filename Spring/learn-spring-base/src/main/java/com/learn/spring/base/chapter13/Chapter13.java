package com.learn.spring.base.chapter13;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter13 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter13.xml");


        SomeWork someWork= (SomeWork) context.getBean("someWork");
        System.out.println(someWork.getName());

       // context.start();
        //  context.registerShutdownHook();

    }
}
