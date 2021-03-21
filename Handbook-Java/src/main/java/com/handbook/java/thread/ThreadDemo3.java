package com.handbook.java.thread;

import org.junit.Test;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ThreadDemo3
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 16:59
 * @Version 1.0
 **/
public class ThreadDemo3 {
    @Test
    public void test1(){
        ImmutableValue immutableValue=new ImmutableValue(5);
        for (int i = 0; i < 100; i++) {
            Thread thread=new Thread(()->{
                ImmutableValue immutableValue2=   immutableValue.add(15);
                System.out.println(immutableValue2.getValue());
            });
            thread.start();
        }
    }
    @Test
    public void test2() throws InterruptedException {
        Calculator calculator=new Calculator();

        for (int i = 0; i <2 ; i++) {
            Thread thread=new Thread(()->{
                int rd=Math.random()>0.5?1:0;
                ImmutableValue immutableValue=new ImmutableValue(10);
                calculator.setValue(immutableValue);
                calculator.add(rd);
                System.out.println("【"+immutableValue.getValue()+" + "+rd+" 】="+calculator.getValue().getValue());
            });
            thread.start();
        }

    }
}
