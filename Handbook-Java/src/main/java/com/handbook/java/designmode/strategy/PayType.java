package com.handbook.java.designmode.strategy;

import com.handbook.java.designmode.strategy.impl.AliPay;
import com.handbook.java.designmode.strategy.impl.JdPay;
import com.handbook.java.designmode.strategy.impl.UnionPay;
import com.handbook.java.designmode.strategy.impl.WeChatPay;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   枚举类+策略模式 避免了if else  switch
 * @ClassName PayType
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:01
 * @Version 1.0
 **/
public enum PayType {
    ALI_PAY(new AliPay()),WECHAT_PAY(new WeChatPay()),JD_PAY(new JdPay()),UNION_PAY(new UnionPay());
    private PayMent payMent;
    PayType(PayMent payMent) {
        this.payMent = payMent;
    }
    public PayMent get(){
        return this.payMent;
    }
}
