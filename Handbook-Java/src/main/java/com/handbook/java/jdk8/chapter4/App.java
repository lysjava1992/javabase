package com.handbook.java.jdk8.chapter4;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *      java8之前已有的函数式接口
 *       java.lang.Runnable
 *       java.util.concurrent.Callable
 *       java.util.Comparator
 *       java.io.FileFilter
 *       java.nio.file.PathMatcher
 *       ........
 *
 *     JDK 1.8 新增加的函数接口：
 *      java.util.function
 *      java.util.function 它包含了很多类，用来支持 Java的 函数式编程
 *
 * @ClassName App
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 10:28
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        HelloService helloService=msg -> System.out.println(msg);
        helloService.sayHello("Hello");
    }
}
