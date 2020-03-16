package com.handbook.java.jdk8.chapter4;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *     函数式接口
 *          有且只有一个抽象方法，但是可以有多个非抽象方法的接口
 *          函数式接口可以被隐式转换为lambda表达式
 *          用 @FunctionalInterface注解修饰
 * @ClassName HelloService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 10:37
 * @Version 1.0
 **/
@FunctionalInterface
public interface HelloService {
    void sayHello(String msg);
    default void  sayHello2(String msg){
       System.out.println("sayHello2 :"+msg);
    }
}
