package com.learn.spring.base.chapter15;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter15 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter15.xml");
        BeanFactory beanFactory=context;
        TestBeanOne beanOne= (TestBeanOne) context.getBean("beanOne");
        beanOne.doSome();
        TestBeanTwo beanTwo= (TestBeanTwo) context.getBean("beanTwo");
        beanTwo.doSome();
        context.registerShutdownHook();

    }
}
