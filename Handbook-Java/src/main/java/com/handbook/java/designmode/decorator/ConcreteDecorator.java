package com.handbook.java.designmode.decorator;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ConcreteDecorato
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/26 11:15
 * @Version 1.0
 **/
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void doThing() {
        System.out.println("优化包装....");
        super.doThing();
    }
}
