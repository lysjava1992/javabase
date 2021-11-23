package com.handbook.java.thread;

/**
 *  假唤醒
 *   自旋锁
 */
public class ThreadWaitNotify2 {
    Object lock = new Object();
    boolean wasSignalled = false;
    public void doWait() {
        synchronized (lock) {
            while (!wasSignalled){
                try {
                    System.out.println(Thread.currentThread().getName()+"[-wait-]");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName()+"[-recover-]");
                } catch (InterruptedException e) { e.printStackTrace(); }
            }
                wasSignalled=false;
        }
    }
    public void doNotify() {
        synchronized (lock) {
            wasSignalled =true;
            lock.notify();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadWaitNotify2 waitNotify=new ThreadWaitNotify2();
        System.out.println("唤醒----下一个通知");
        new Thread(()->{ waitNotify.doNotify();});
        Thread.sleep(1000);
        new Thread(()->{waitNotify.doWait();});
    }
}
