package com.handbook.java.thread;

import java.util.concurrent.locks.LockSupport;

/**
 *  使用LockSupport对线程进行
 *   沉睡（park）|(unPark)唤醒操作
 *   与wait()/notify()相比park没有顺序
 *    park不会释放锁!!!!!!!!!!
 *    unpark（指定线程）
 * @Author Mr.Luan
 **/
public class ThreadWaitNotify3 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            System.out.println("----执行----");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("----沉睡----");
            // 使线程沉睡
            LockSupport.park();
            System.out.println("----完毕----");
        });
        thread.start();
        Thread.sleep(5000);
        // 唤醒指定线程
        LockSupport.unpark(thread);
        System.out.println("----唤醒----");
    }
}









