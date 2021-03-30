package com.learn.spring.base.chapter11;

import org.springframework.beans.factory.config.Scope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.SimpleThreadScope;

public class Chapter {
    public static void main(String[] args) {
        Scope threadScope = new SimpleThreadScope();
     ApplicationContext ioc=new  ClassPathXmlApplicationContext("spring_chapter11.xml");
      Machine2 machine= (Machine2) ioc.getBean("machine2");
      machine.doWork();
      machine.doWork();
     MyValueCalculator calculator= (MyValueCalculator) ioc.getBean("myValueCalculator");
     System.out.println(calculator.computeValue("OK"));
    }
}
