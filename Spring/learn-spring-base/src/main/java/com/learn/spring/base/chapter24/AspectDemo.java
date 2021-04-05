package com.learn.spring.base.chapter24;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Component
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass=true,exposeProxy=true)
@Order(1)
public class AspectDemo {

    @Before("execution(*(com.learn.spring.base.chapter24.SomeService+).doWork(..))")
    public void before2(){
        System.out.println("AspectDemo方法即将执行1");
    }
//    @Order(2)
//    @Before("execution(*(com.learn.spring.base.chapter24.SomeService+).doWork(..))&&args(int)")
//    public void before(){
//        System.out.println("方法即将执行2");
//    }


}
