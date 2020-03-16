package com.handbook.java.designmode.strategy;

import lombok.Data;

import java.util.UUID;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Order
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 9:58
 * @Version 1.0
 **/
@Data
public class Order {
    private String id;
    private double amount;

    public Order(double amount) {
        this.amount = amount;
        this.id= UUID.randomUUID().toString();
    }
    public PayStatus pay(PayType payType){
        return payType.get().pay(this);
    }
}
