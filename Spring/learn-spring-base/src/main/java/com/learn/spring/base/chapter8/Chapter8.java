package com.learn.spring.base.chapter8;

import com.learn.spring.base.chapter8.service.BaseService5;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter8 {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter8.xml");
        BaseService5 baseService5= (BaseService5) context.getBean("baseService5Impl");
        baseService5.select();
        System.out.println("==========================");
        baseService5.add();
        System.out.println("==========================");
        baseService5.remove();
    }

}
