package com.learn.spring.base.chapter24;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(100)
public class AspectDemo2 {

    @Before("execution(*(com.learn.spring.base.chapter24.SomeService+).doWork(..))")
    public void before2(){
        System.out.println("AspectDemo2 方法即将执行1");
    }



}
