package com.handbook.java.thread;

/**
 * hasDataToProcess() 和 setHasDataToProcess（）
 *   1. 都不加synchronized可以通知进入执行
 *   2. 其中一个加synchronized则该方法同时只有一个线程执行，另一个方法可以并发执行
 *   3. 两个都加synchronized，则同一时刻只有一个线程一个方法执行
 * @Author Mr.Luan
 **/
public class ThreadSignal {
    protected boolean hasDataToProcess = false;

    public synchronized boolean hasDataToProcess() throws InterruptedException {
        System.out.println("----------get start---------");
        Thread.sleep(5000);
        System.out.println("----------get end---------");
        return this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasData) throws InterruptedException {
        System.out.println("----------set start---------");
        Thread.sleep(5000);
        System.out.println("----------set end---------");
        this.hasDataToProcess = hasData;
    }

    public static void main(String[] args) {
        ThreadSignal threadSignal = new ThreadSignal();
        Thread thread = new Thread(() -> {
            try {
                threadSignal.setHasDataToProcess(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                System.out.println(threadSignal.hasDataToProcess());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread.start();
    }

}
