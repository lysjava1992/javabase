package com.handbook.java.jdk8.chapter1;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *        接口的作用是定义一套标准 约束 规范等，接口中的方法只声明方法的签名，不提供相应的方法体，
 *             方法体由对应的实现类去实现
 *         jdk8打破这一规定，接口中可以有方法体，但必须用static 或者default来修饰
 * @ClassName Hello
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 8:35
 * @Version 1.0
 **/
public interface Hello {
    void sayHello1();
    default void sayHello2(){
        System.out.println("Hello 2");
        }
     static void sayHello3(){
        System.out.println("Hello 3");
     }

}
