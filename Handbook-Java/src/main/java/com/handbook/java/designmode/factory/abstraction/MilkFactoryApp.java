package com.handbook.java.designmode.factory.abstraction;

import com.handbook.java.designmode.factory.MengNiu;
import com.handbook.java.designmode.factory.Milk;
import com.handbook.java.designmode.factory.SanYuan;
import com.handbook.java.designmode.factory.method.GuangMingFactory;
import com.handbook.java.designmode.factory.method.MengNiuFactory;
import com.handbook.java.designmode.factory.method.SanYuanFactory;
import com.handbook.java.designmode.factory.method.YiLiFactory;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MilkFactoryApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:52
 * @Version 1.0
 **/
public class MilkFactoryApp extends MilkAbstractFactory {
    @Override
    public Milk getMengNiu() {
        return new MengNiuFactory().getMilk();
    }

    @Override
    public Milk getYiLi() {
        return new YiLiFactory().getMilk();
    }

    @Override
    public Milk getGuangMing() {
        return new GuangMingFactory().getMilk();
    }

    @Override
    public Milk getSunYuan() {
        return new SanYuanFactory().getMilk();
    }
}
