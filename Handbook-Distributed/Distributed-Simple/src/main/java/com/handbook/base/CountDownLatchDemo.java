package com.handbook.base;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 *  CountDownLatch 多线程控制工具，用来控制线程等待
 *     让某个线程等待直到倒计时结束，再开始执行
 * @author: Mr.Luan
 * @create: 2019-11-01 10:41
 **/
public class CountDownLatchDemo {
    /**
     * 指定计数
     */
    private static CountDownLatch count=new CountDownLatch(3);

    public static Runnable r1=new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("r1 run ......");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //倒计时
                count.countDown();
            }
        }
    };
    public static Runnable r2=new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("r2 run ......");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //倒计时
                count.countDown();
            }
        }
    };
    public static Runnable r3=new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("r3 run ......");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //倒计时
                count.countDown();
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=========start============");
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r3).start();

        //阻塞主线程  count.countDown() 0之后再往下执行
        count.await();
        System.out.println("=========end============");
    }
}
