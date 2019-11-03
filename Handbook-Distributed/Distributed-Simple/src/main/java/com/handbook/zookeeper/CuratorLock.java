package com.handbook.zookeeper;

import com.sun.org.apache.regexp.internal.RE;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.CountDownLatch;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *  利用Curator客户端提供的锁来实现
 *    InterProcessMutex
 * @ClassName CuratorLock
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 15:45
 * @Version 1.0
 **/
public class CuratorLock {
    public static CuratorFramework getZookeeper(String ips) {
        CuratorFramework zookeeper= CuratorFrameworkFactory.builder()
                    .connectString(ips)
                    .sessionTimeoutMs(4000)
                     .retryPolicy(new ExponentialBackoffRetry(1000,3))
                    .build();
        zookeeper.start();
        return zookeeper;
        }

    public static void main(String[] args) {
        int count=10;
        final int[] tall = {6};
        CountDownLatch countDownLatch=new CountDownLatch(count);
        for (int i=0;i<count;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InterProcessMutex  ipm=new InterProcessMutex(getZookeeper("192.168.1.108:2181"),"/locks");
                        countDownLatch.await();
                        ipm.acquire();
                        if(tall[0] >=1){
                            Thread.sleep(1000);
                            tall[0]--;
                            System.out.println("抢购成功,库存"+tall[0]);
                        }else {
                            System.out.println("抢购失败,库存"+tall[0]);
                        }
                        ipm.release();
                    }catch (Exception e){
                     e.printStackTrace();
                    }
                }
            }).start();
            countDownLatch.countDown();
        }
    }
}
