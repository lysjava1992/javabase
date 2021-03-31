package com.learn.spring.base.chapter17.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
@Primary
@Service()
public class HelloServiceImpl1 implements HelloService{
    public void sayHello() {
        System.out.println("HelloServiceImpl1:【Hello】");
    }
}
