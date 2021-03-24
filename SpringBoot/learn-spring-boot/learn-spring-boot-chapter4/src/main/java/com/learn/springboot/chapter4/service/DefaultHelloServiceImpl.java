package com.learn.springboot.chapter4.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultHelloServiceImpl implements HelloService{
    @Override
    public void sayHi() {
        System.out.println("Hello");

    }
}
