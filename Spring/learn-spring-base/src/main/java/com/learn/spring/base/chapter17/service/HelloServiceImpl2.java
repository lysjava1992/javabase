package com.learn.spring.base.chapter17.service;

import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl2 implements HelloService{
    public void sayHello() {
        System.out.println("HelloServiceImpl2:【Hi】");
    }
}
