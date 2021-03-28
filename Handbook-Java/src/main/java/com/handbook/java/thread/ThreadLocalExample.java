package com.handbook.java.thread;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ThreadLocal
 */
public class ThreadLocalExample {
    private ThreadLocal<Integer> localValue=new ThreadLocal<>();
    private Integer value;

    public Integer getLocalValue() {
        return localValue.get();
    }
    public void setLocalValue(Integer localValue) {
        this.localValue.set(localValue);
    }
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }

    public static void main(String[] args) {
        ThreadLocalExample example=new ThreadLocalExample();
        Random random=new Random();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                 int value=random.nextInt();
                example.setValue(value);
                example.setLocalValue(value);
                try {
                    Thread.sleep(5);
                    //   System.out.println(Thread.currentThread().getName()+" value ["+example.getValue()+"]");
                   System.out.println(Thread.currentThread().getName()+" localValue["+example.getLocalValue()+"]");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
