package com.handbook.java.designmode.proxy.statical;

import com.handbook.java.designmode.proxy.Person;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HouseAgent
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 9:32
 * @Version 1.0
 **/
public class HouseAgent {
    private Person person;

    public HouseAgent(Person doctor) {
        this.person = doctor;
    }

    public void BuyHouse(){
        System.out.println("中介：筛选查找房源");
        this.person.buyHouse();
    }
}
