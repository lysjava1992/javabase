package com.handbook.java.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ScheduledExecutorTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 12:47
 * @Version 1.0
 **/
public class ScheduledExecutorTest {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newScheduledThreadPool(2);
//        service.scheduleAtFixedRate(()->{
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+" 执行 --"+System.currentTimeMillis());
//        },1000,5000, TimeUnit.MILLISECONDS);
        service.scheduleWithFixedDelay(()->{
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" 执行 --"+System.currentTimeMillis());
        },1000,5000, TimeUnit.MILLISECONDS);

    }
}
