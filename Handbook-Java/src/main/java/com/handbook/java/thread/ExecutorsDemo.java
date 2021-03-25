package com.handbook.java.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class ExecutorsDemo {



    public static void main(String[] args) throws Exception{
//---------------------executorService-----------------
//        ExecutorService executorService=Executors.newSingleThreadExecutor();
//        executorService.submit(()->{
//            System.out.println(Thread.currentThread().getName());
//        });
//        Future<String> future=executorService.submit(()->{
//            return Thread.currentThread().getName();
//        });
//        System.out.println(future.get());
//        executorService.shutdown();
         //executorService.shutdownNow();

//---------------------ScheduledExecutorService 1-----------------
        ScheduledExecutorService executor= Executors.newSingleThreadScheduledExecutor();
        executor.schedule(()->{System.out.println("Hello");},10,TimeUnit.SECONDS);
        executor.scheduleWithFixedDelay(()->{System.out.println("执行周期5s");},0,5,TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(()->{System.out.println("执行周期5s");},0,5,TimeUnit.SECONDS);
//        executor.shutdown();

    }
}
