package com.handbook.java.jdk8.ssy;

@FunctionalInterface
public interface MyInterface {
    void test();
    //void test1();

    /**
     * toString 对于Object中的方法
     * 并不会违背函数式接口
     * 有且只有一个抽象方法的原则
     * @return
     */
    String toString();
}
