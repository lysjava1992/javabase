package com.learn.spring.base.chapter12;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter12 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter12.xml");
        for (int i = 0; i < 6; i++) {
            ScopeTest scopeTest= (ScopeTest) context.getBean("scopeTest");
            scopeTest.sayId();
        }
    }
}
