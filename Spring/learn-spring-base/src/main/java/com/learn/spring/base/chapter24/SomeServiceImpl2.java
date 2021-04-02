package com.learn.spring.base.chapter24;

import org.springframework.stereotype.Service;

@Service("someService2")
public class SomeServiceImpl2 implements SomeService{
    public void doWork() {
        System.out.println("--[ 2 : doWork() ]--");
    }

    public void doWork(String value) {
        System.out.println("--[ 2 : doWork(String value) ]--");
    }

    public void doWork(int value) {
        System.out.println("--[ 2 : doWork(int value) ]--");
    }

    public void doWork(String type, int value) {
        System.out.println("--[ 2 : doWork(String type, int value) ]--");
    }
}
