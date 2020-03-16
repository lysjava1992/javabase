package com.handbook.java.designmode.singleton.hungry;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * 饿汉式 单例加载
 * @ClassName Hungry
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 16:19
 * @Version 1.0
 **/
public class Hungry {
    private Hungry() {
    }
    private final static Hungry hungry=new Hungry();
    public static  Hungry getInstance(){

        return hungry;
    }
}
