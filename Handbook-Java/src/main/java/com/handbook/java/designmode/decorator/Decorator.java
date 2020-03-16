package com.handbook.java.designmode.decorator;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *     装饰类，实现了Component接口的同时还内部维护了一个实例
 *     它仅仅是一个声明：我要生产出一些用于装饰的子类。子类才是具有装饰功能的产品
 * @ClassName Decorator
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 11:11
 * @Version 1.0
 **/
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doThing() {
        component.doThing();
    }
}
