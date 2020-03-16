package com.handbook.java.designmode.factory;

import com.handbook.java.designmode.factory.abstraction.MilkAbstractFactory;
import com.handbook.java.designmode.factory.abstraction.MilkFactoryApp;
import com.handbook.java.designmode.factory.method.GuangMingFactory;
import com.handbook.java.designmode.factory.method.MilkFactory;
import com.handbook.java.designmode.factory.simple.SimpleFactory;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AppTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:26
 * @Version 1.0
 **/
public class AppTest {
    public static void main(String[] args) {
       //非工厂模式 直接参与  若新增一个品牌则需要外部自己去new
        Milk milk=new MengNiu();
        System.out.println(milk.createMilke());

        // 简单/静态工厂模式  提出需求  若新增一个品牌则需要外部自己去提出需求
        SimpleFactory factory=new SimpleFactory();

        Milk milk1=factory.getMike("蒙牛");
        System.out.println(milk1.createMilke());
        Milk milk2=factory.getMike("伊利");
        System.out.println(milk2.createMilke());

        //工厂方法模式  选择工厂 若新增一个品牌则需要外部自己去联系工厂
        MilkFactory milkFactory=new GuangMingFactory();
        Milk milk3=milkFactory.getMilk();
        System.out.println(milk3.createMilke());

        //抽象工厂模式  选择产品   若新增品牌外部直接选择即可
        MilkFactoryApp milkFactoryApp=new MilkFactoryApp();
        Milk milk4=milkFactoryApp.getGuangMing();
        System.out.println(milk4.createMilke());
    }
}
