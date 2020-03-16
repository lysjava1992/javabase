package com.handbook.java.designmode.factory.method;

import com.handbook.java.designmode.factory.Milk;
import com.handbook.java.designmode.factory.SanYuan;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SanYuanFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:58
 * @Version 1.0
 **/
public class SanYuanFactory implements MilkFactory {
    @Override
    public Milk getMilk() {
        return new SanYuan();
    }
}
