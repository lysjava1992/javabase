package com.handbook.java.jdk8.chapter1;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *
 *    对于Hello接口中的sayHello1（）方法是抽象方法，所有实现类必须实现
 *
 *    对于Hello接口中的sayHello2（）方法已有默认方法，则可以根据情况选择是否重写
 *
 *    对于Hello 接口中的sayHello3（）静态方法 不能实现，只能接口调用 Hello.sayHello3（）
 *
 * @ClassName Ihello
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 8:41
 * @Version 1.0
 **/
public class Ihello implements Hello {
    @Override
    public void sayHello1() {
        System.out.println("Hello ");
    }

//    @Override
//    public void sayHello2() {
//
//    }
}
