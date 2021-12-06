package com.handbook.java.thread;

import java.util.Random;

/**
 * Calculator 具有可变性
 *
 *
 * @ClassName Calculator
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 17:10
 * @Version 1.0
 **/
public class Calculator {
    private ImmutableValue currentValue=null;


    public   ImmutableValue getValue(){
        return currentValue;
    }

    public  void setValue(ImmutableValue newValue){
        this.currentValue = newValue;
    }

    public void add(int newValue){
        this.currentValue = this.currentValue.add(newValue);
    }

    public static void main(String[] args) {
        Calculator calculator=new Calculator();
        Random random=new Random();
        for (int i = 0; i <10 ; i++) {
            Thread thread=new Thread(()->{
                //int rd=Math.random()>0.5?1:0;
                int rd=random.nextInt(20);
                ImmutableValue immutableValue=new ImmutableValue(10);
                calculator.setValue(immutableValue);
                calculator.add(rd);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // ImmutableValue是不可变的
                // 但calculator是可变的
                // 此时是非线程安全的
                System.out.println("【"+immutableValue.getValue()+" + "+rd+" 】="+calculator.getValue().getValue());
            });
            thread.start();
        }
    }
}
