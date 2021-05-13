package com.simple.actor.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        tryProcessQueue();
        Thread.sleep(100000000);
    }
    static ExecutorService executorService= Executors.newWorkStealingPool(10);
    static AtomicBoolean busy=new AtomicBoolean(false);
    private static void tryProcessQueue() {
      executorService.execute(ExecutorTest::process);

    }
    private static void process()  {
      for (int i = 0; i < 3; i++) {
            work();
    }
     //  executorService.execute(ExecutorTest::process);
        executorService.execute(ExecutorTest::tryProcessQueue);

    }

    private static void work() {
        try {
            System.out.println(Thread.currentThread().getName()+"  开始<");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName()+"  结束   >");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
