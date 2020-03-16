package com.handbook.java.designmode.proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Doctor
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 9:30
 * @Version 1.0
 **/
public class Doctor implements Person {
    @Override
    public void buyHouse(){
        System.out.println("医生 ：买房子");
    }

    @Override
    public void buyRice() {
        System.out.println("医生 ：订午餐");
    }
}
