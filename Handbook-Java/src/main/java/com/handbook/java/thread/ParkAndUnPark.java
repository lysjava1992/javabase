package com.handbook.java.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ParkAndUnPark
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 11:22
 * @Version 1.0
 **/
public class ParkAndUnPark {
    public static void main(String[] args) throws InterruptedException {
        Thread thread=new Thread(()->{
            System.out.println("----执行----");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.park();
            System.out.println("----完毕----");
        });
        thread.start();
        Thread.sleep(5000);
        LockSupport.unpark(thread);
    }
}









