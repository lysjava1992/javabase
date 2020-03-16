package com.handbook.java.designmode.factory.method;

import com.handbook.java.designmode.factory.MengNiu;
import com.handbook.java.designmode.factory.Milk;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MengNiuFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:43
 * @Version 1.0
 **/
public class MengNiuFactory implements MilkFactory {
    @Override
    public Milk getMilk() {
        return new MengNiu();
    }
}
