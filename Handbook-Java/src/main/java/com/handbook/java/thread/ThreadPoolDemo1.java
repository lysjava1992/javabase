package com.handbook.java.thread;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ThreadPoolDemo1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 12:58
 * @Version 1.0
 **/
public class ThreadPoolDemo1 {
    static class ThreadNameFactory implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r,"Name-"+ UUID.randomUUID());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor=new ThreadPoolExecutor(2,
                        5,
                        20,
                        TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(10),
                new ThreadNameFactory(),
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

                    }
                });
        for (int i = 0; i <16 ; i++) {
            executor.execute(()->{
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"--完毕--");
            });
        }
        Thread.sleep(1000);
        executor.shutdown();
    }
}
