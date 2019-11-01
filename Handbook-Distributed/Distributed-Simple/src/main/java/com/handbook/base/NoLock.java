package com.handbook.base;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-01 16:12
 **/
public class NoLock {
    public static void main(String[] args) {
        final int[] tall = {80};
        final CountDownLatch downLatch=new CountDownLatch(100);
        for (int i=0;i<100;i++){
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        // 要确保一个用户拿到资源才可以
                        if(tall[0] >=1){
                            Thread.sleep(1000);
                            tall[0] = tall[0] -1;
                            System.out.println("用户"+ index +" 抢购成功；库存"+tall[0]);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
            downLatch.countDown();
        }
    }
}
