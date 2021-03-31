package com.learn.spring.base.chapter15;

import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestBeanTwo {
    public void  doSome(){
        System.out.println("-----[TestBeanTwo]被调用-----");
    }
    @PostConstruct
    public void init(){
        System.out.println("-----[TestBeanTwo]实例化注入完毕-----");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("-----[TestBeanTwo]即将销毁-----");
    }
}
