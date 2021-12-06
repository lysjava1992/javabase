package com.handbook.java.thread;

import java.util.Random;

/**
 *   不可变性
 *
 * @ClassName ImmutableValue
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 17:00
 * @Version 1.0
 **/
public class ImmutableValue {
    /**
     * private 修饰
     * 只有get方法访问
     * 不变性
     */
    private int value;

    public ImmutableValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     *  每次add
     *   实际又返回了一个新的实例
     *   因此对于全局的ImmutableValue而言是不可变的
     *   多线程下每个线程其实拿到的都是一个新的实例
     * @param valueToAdd
     * @return
     */
    public ImmutableValue add(int valueToAdd){
        return new ImmutableValue(value+valueToAdd);
    }


    public static void main(String[] args) throws InterruptedException {
        ImmutableValue immutableValue=new ImmutableValue(5);
        Random random=new Random();
        for (int i = 0; i < 10; i++) {
            Thread thread=new Thread(()->{
                // add 返回的是一个新的ImmutableValue实例
                // 即immutableValue!=immutableValue2
                // 因此是线程安全的
                ImmutableValue immutableValue2=   immutableValue.add(random.nextInt(10));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 从输出可知是线程安全的
                System.out.println(immutableValue2.getValue());
            });
            thread.start();
        }
    }
}
