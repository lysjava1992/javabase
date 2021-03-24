package com.learn.test.mock;

public class DefalutMachine implements Machine{
    @Override
    public void produce() {
        System.out.println("-----produce()------");
    }

    @Override
    public String produce(String type) {
        return "produce(String type)";
    }

    @Override
    public String produce2() {
        return "produce2(String type)";
    }

    @Override
    public String produce(String type, int size) {
        return "produce(String type, int size) ";
    }
}
