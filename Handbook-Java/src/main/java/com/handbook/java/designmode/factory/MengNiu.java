package com.handbook.java.designmode.factory;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MengNiu
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:23
 * @Version 1.0
 **/
public class MengNiu implements Milk {
    @Override
    public String createMilke() {
        return "蒙牛";
    }
}
