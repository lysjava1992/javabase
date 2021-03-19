package com.handbook.java.jdk8.ssy;

public class Test2 {
    public void myTest(MyInterface myInterface) {
        myInterface.test();
    }

    public static void main(String[] args) {
        Test2 test1 = new Test2();
        test1.myTest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("匿名内部类");
            }
        });
        System.out.println("------------------");
        test1.myTest(() -> System.out.println("函数式接口"));
        System.out.println("------------------");
        test1.myTest(() -> System.out.println("函数式接口"));
        System.out.println("------------------");
        MyInterface myInterface=() -> System.out.println("函数式接口");
        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
    }
}
