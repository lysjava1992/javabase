package com.handbook.java.designmode.factory.abstraction;

import com.handbook.java.designmode.factory.Milk;
import com.handbook.java.designmode.factory.method.GuangMingFactory;
import com.handbook.java.designmode.factory.method.MengNiuFactory;
import com.handbook.java.designmode.factory.method.YiLiFactory;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MikeAbstraFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 11:47
 * @Version 1.0
 **/
public abstract class MilkAbstractFactory {
    public abstract Milk getMengNiu();
    public abstract Milk getYiLi();
    public abstract Milk getGuangMing();
    public abstract Milk getSunYuan();
}
