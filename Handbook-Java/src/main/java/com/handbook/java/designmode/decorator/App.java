package com.handbook.java.designmode.decorator;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName App
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 11:16
 * @Version 1.0
 **/
public class App {
    public static void main(String[] args) {
        Component component=new ConcreteDecorator(new ConcretComponent());
        component.doThing();
    }
}
