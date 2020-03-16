package com.handbook.java.designmode.adapter;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Adapter
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/24 11:31
 * @Version 1.0
 **/
public class Adapter extends TypeCImpl implements MicroUSB {
    @Override
    public void isMicroUSB() {
        isTypeC();
    }
}
