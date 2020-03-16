package com.handbook.java.designmode.strategy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AppTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:19
 * @Version 1.0
 **/
public class AppTest {
    public static void main(String[] args) {
        Order order=new Order(125.6);
       System.out.println(order.pay(PayType.ALI_PAY));
        System.out.println(order.pay(PayType.WECHAT_PAY));
        System.out.println(order.pay(PayType.UNION_PAY));
        System.out.println(order.pay(PayType.JD_PAY));
    }
}
