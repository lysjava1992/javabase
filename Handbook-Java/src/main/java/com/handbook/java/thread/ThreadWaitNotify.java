package com.handbook.java.thread;

public class ThreadWaitNotify {
    Object lock = new Object();
    public void doWait() {
        synchronized (lock) {
           try {
               System.out.println(Thread.currentThread().getName()+"[-wait-]");
               // wait()方法使线程进入等待状态
               // wait()会释放锁
                lock.wait();
                Thread.sleep(5000);
               System.out.println(Thread.currentThread().getName()+"[-recover-]");
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }

    /**
     * 唤醒任一一个等待的线程
     */
    public void doNotify() {
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * 唤醒所有等待的线程
     */
    public void doNotifyAll() {
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadWaitNotify waitNotify=new ThreadWaitNotify();
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
