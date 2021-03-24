package com.handbook.java.thread;

public class MyWaitNotify {
    Object lock = new Object();
    public void doWait() {
        synchronized (lock) {
           try {
               System.out.println(Thread.currentThread().getName()+"[-wait-]");
                lock.wait();
                Thread.sleep(5000);
               System.out.println(Thread.currentThread().getName()+"[-recover-]");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
    public void doNotify() {
        synchronized (lock) {
            lock.notify();
        }
    }
    public void doNotifyAll() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyWaitNotify waitNotify=new MyWaitNotify();
        for (int i = 0; i < 5; i++) {
           new Thread(()->{waitNotify.doWait();}).start();
        }
        Thread.sleep(5000);
        System.out.println("唤醒----下一个通知");
        new Thread(()->{waitNotify.doNotify();}).start();
        Thread.sleep(5000);
        System.out.println("唤醒全部----通知");
        new Thread(()->{waitNotify.doNotifyAll();}).start();
    }
}
