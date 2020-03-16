package com.learn.spring.base.chapter2.bean;

/**
 * 静态工厂
 *
 * @ClassName StaticFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 20:06
 * @Version 1.0
 **/
public class StaticFactory {
    public static Toy createToy(){
        Toy toy=new Toy();
        toy.setFactory("静态工厂建造");
        toy.setName("玩具狗");
        toy.setType("毛绒玩具");
        toy.setSerial(System.currentTimeMillis());
        return toy;
    }
}
