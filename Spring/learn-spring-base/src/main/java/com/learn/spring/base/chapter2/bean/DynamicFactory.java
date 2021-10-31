package com.learn.spring.base.chapter2.bean;

/**
 * 动态工厂
 *
 * @ClassName Factory1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 19:57
 * @Version 1.0
 **/
public class DynamicFactory {
    public Toy createToy(){
        System.out.println("--------创建-----");
        Toy toy=new Toy();
        toy.setFactory("动态工厂建造");
        toy.setName("机器狗");
        toy.setType("电子产品");
        toy.setSerial(System.currentTimeMillis());
        return toy;
    }
}
