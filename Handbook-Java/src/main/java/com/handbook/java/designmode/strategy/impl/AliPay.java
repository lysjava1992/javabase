package com.handbook.java.designmode.strategy.impl;

import com.handbook.java.designmode.strategy.Order;
import com.handbook.java.designmode.strategy.PayMent;
import com.handbook.java.designmode.strategy.PayStatus;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AliPay
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:04
 * @Version 1.0
 **/
public class AliPay implements PayMent {
    @Override
    public PayStatus pay(Order order) {

        return new PayStatus(200,"订单号："+order.getId(),"支付宝支付交易金额：【"+order.getAmount()+"】");
    }
}
