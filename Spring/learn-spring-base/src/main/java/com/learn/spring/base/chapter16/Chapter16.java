package com.learn.spring.base.chapter16;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter16 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter16.xml");
        BeanDemo beanDemo=context.getBean(BeanDemo.class);
        beanDemo.doSome();
    }
}
