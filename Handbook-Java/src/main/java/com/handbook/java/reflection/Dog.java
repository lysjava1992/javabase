package com.handbook.java.reflection;

public class Dog implements Animal {
    @Override
    public void run() {
        System.out.println("dog is running ");
    }

    public void bark() {
        System.out.println("wang .... ");
    }
}
