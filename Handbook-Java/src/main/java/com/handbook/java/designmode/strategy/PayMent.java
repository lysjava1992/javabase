package com.handbook.java.designmode.strategy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName PayMent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:03
 * @Version 1.0
 **/
public interface PayMent {
    PayStatus pay(Order order);
}
