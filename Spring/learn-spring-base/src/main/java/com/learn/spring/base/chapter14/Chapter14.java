package com.learn.spring.base.chapter14;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter14 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter14.xml");

                DerivedTestBean derivedTestBean= (DerivedTestBean) context.getBean("derivedTestBase");
                System.out.println(derivedTestBean);
    }
}
