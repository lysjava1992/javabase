package com.handbook.java.thread.lock;

import java.util.Random;

public class CustomLockTest {
    CustomLock lock=new CustomLock();
    private Integer value;
    public Integer getValue(Integer value) throws Exception{
        lock.lock();
        int result=0;
        this.value = value;
        try {
         //   System.out.println(Thread.currentThread().getName()+"   "+this.value);
            Thread.sleep(5);
            result=this.value++;
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static void main(String[] args) {
        CustomLockTest customLockTest=new CustomLockTest();
        Random random=new Random();
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                int value=random.nextInt();
                try {
                    System.out.println("value : "+customLockTest.getValue(value));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
