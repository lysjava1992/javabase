package com.handbook.java.thread.guava;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;


public class ListenFutureApp {
    public static void main(String[] args) throws Exception {
        // receiveValue();
        // callBackMethod();
        // receivePoolValue();
        receivePoolValue2();

    }

    /**
     *  guava线程池回调参数注册
     *  futureCallback
     *    Futures.addCallback(listenableFuture, futureCallback, pool);
     */
    private static void receivePoolValue2() throws InterruptedException {
        int size = 5;
        //MoreExecutors.listeningDecorator就是包装了一下ThreadPoolExecutor，目的是为了使用ListenableFuture
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(size));
        // 回调处理函数
        FutureCallback<Long> futureCallback = new FutureCallback<Long>() {
            @Override
            public void onSuccess(Long result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        };
        for (int i = 0; i < size; i++) {
            Futures.addCallback(pool.submit(() -> System.currentTimeMillis()), futureCallback, pool);
        }
        Thread.sleep(5000);
        pool.shutdown();
    }

    /**
     * 线程池返回结果接收
     */
    private static void receivePoolValue() {
        int size = 5;
        //MoreExecutors.listeningDecorator就是包装了一下ThreadPoolExecutor，目的是为了使用ListenableFuture
        ListeningExecutorService pool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(size));
        List<ListenableFuture<Long>> listenableFutureList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            listenableFutureList.add(pool.submit(() -> System.currentTimeMillis()));
        }
        listenableFutureList.forEach(x -> {
            try {
                System.out.println((x.get()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
    }

    /**
     * 注册回调方法
     */
    private static void callBackMethod() {
        ListenableFutureTask<Long> task = ListenableFutureTask.create(() ->
        {
            System.out.println(Thread.currentThread().getName());
            return System.currentTimeMillis();
        });
        new Thread(task).start();
        //增加回调方法，MoreExecutors.directExecutor()返回guava默认的Executor，
        // 执行回调方法不会新开线程，所有回调方法都在当前线程做(可能是主线程或者执行ListenableFutureTask的线程)。
        //guava异步模块中参数有Executor的方法，
        // 一般还会有一个没有Executor参数的重载方法，使用的就是MoreExecutors.directExecutor()
        task.addListener(() -> {
            // 在main中执行
            // 回调方法虽然是implement Runnable接口
            // 但不会在新线程中执行
            try {
                System.out.println(task.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }, MoreExecutors.directExecutor());
    }

    /**
     * 使用Guava 的 ListenableFutureTask接收线程执行的返回值
     */
    public static void receiveValue() throws ExecutionException, InterruptedException {
        ListenableFutureTask<String> task = ListenableFutureTask.create(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        // 启动
        new Thread(task).start();
        // 获取结果
        String value = task.get();
        System.out.println(value);
    }


}
