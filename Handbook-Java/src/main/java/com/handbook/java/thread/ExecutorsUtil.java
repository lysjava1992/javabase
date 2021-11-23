package com.handbook.java.thread;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程管理
 */
public class ExecutorsUtil {

    public static void main(String[] args) throws Exception{

        scheduledExecutorService();
    }

    /**
     * 声明周期性线程池
     */
    public static void  scheduledExecutorService(){

        ScheduledExecutorService executor= Executors.newSingleThreadScheduledExecutor();
        //声明一个线程，在启动后延迟10秒执行
        executor.schedule(()->{System.out.println("Hello");},10,TimeUnit.SECONDS);

        //声明一个周期性线层，延迟0S启动，每执行完一次间隔5分钟执行一次
        //若任务耗时为2s 则第二次在0s+2s+5s执行
        executor.scheduleWithFixedDelay(()->{System.out.println("执行周期5s");},0,5,TimeUnit.SECONDS);
        //声明一个周期性线层，延迟0S启动，间隔5分钟执行一次
        //若任务耗时<=5  则第二次在0s+5s执行
        //若任务耗时>5   则第二次在0s+任务耗时执行
        executor.scheduleAtFixedRate(()->{System.out.println("执行周期5s");},0,5,TimeUnit.SECONDS);
//        executor.shutdown();
    }
    /**
     * 线程池工具
     */
    public static void  executorService() throws ExecutionException, InterruptedException {
        //声明一个只有一个线程的线程池
        ExecutorService executorService=Executors.newSingleThreadExecutor();
        // 无返回值
        executorService.submit(()->{
            System.out.println(Thread.currentThread().getName());
        });

        //有返回值的线程返回值接收
        Future<String> future=executorService.submit(()->{
            return Thread.currentThread().getName();
        });
        System.out.println(future.get());
        // 任务完毕 退出
        executorService.shutdown();
        // 立即退出
        //executorService.shutdownNow();
    }
}
