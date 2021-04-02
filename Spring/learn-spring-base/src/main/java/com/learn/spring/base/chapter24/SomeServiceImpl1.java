package com.learn.spring.base.chapter24;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class SomeServiceImpl1 implements SomeService{
    public void doWork() {
       System.out.println("--[ 1 : doWork() ]--");
    }

    public void doWork(String value) {
        System.out.println("--[ 1 : doWork(String value) ]--");
    }

    public void doWork(int value) {
        System.out.println("--[ 1 : doWork(int value) ]--");
    }

    public void doWork(String type, int value) {
        System.out.println("--[ 1 : doWork(String type, int value) ]--");
    }
}
