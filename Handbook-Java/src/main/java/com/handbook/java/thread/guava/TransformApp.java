package com.handbook.java.thread.guava;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 转换
 */
public class TransformApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //transformSingle();
        transformList();
    }

    private static void transformList() {
        int size = 10;
        List<ListenableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            futures.add(add(i));
        }
        FutureCallback callback = new FutureCallback() {
            @Override
            public void onSuccess(Object result) {
                System.out.println(result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println(t.getMessage());
            }
        };
        ListenableFuture transform = Futures.transform(Futures.allAsList(futures), SUM_ALL_INTEGERS, MoreExecutors.directExecutor());
        Futures.addCallback(transform, callback, MoreExecutors.directExecutor());
    }

    public static final Function<List<Integer>, Integer> SUM_ALL_INTEGERS = input -> input.size();

    public static ListenableFuture<Integer> add(int value) {
        return Futures.immediateFuture(value * 10);
    }

    /**
     * 单个线程的
     */
    private static void transformSingle() {
        ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        // 最初的工作线程
        ListenableFuture future = service.submit(System::currentTimeMillis);
        ListenableFuture transform = Futures.transform(future, x -> {
            // 此处不会在新线程中处理
            // 此处负责对future的结果进行转换
            System.out.println(Thread.currentThread().getName());
            Long times = (Long) x;
            if (times % 2 == 0) {
                return "true";
            }
            return false;
        }, MoreExecutors.directExecutor());

        //注册回调函数
        Futures.addCallback(transform, new FutureCallback<Object>() {
            /**
             * @param result 是经过转换的结果
             */
            @Override
            public void onSuccess(Object result) {
                System.out.printf("success with: %s%n", result);
            }

            @Override
            public void onFailure(Throwable thrown) {
                System.out.printf(thrown.getMessage());
            }
        }, MoreExecutors.directExecutor());
    }


}
