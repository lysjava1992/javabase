package com.handbook.java.thread;

import java.util.ArrayList;
import java.util.List;

public class FairLockTest {
    private boolean isLock = false;
    private List<ThreadMonitor> waitList = new ArrayList<>();
    private Thread lockingThread = null;
    public void lock() throws Exception {
        System.out.println("---lock---"+Thread.currentThread().getName());
        //每次调用lock,就会对尝试拿锁的线程建一个监控对象
        ThreadMonitor monitor = new ThreadMonitor();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            // 监控对象在list中的顺序，即其所监控的线程的等待殊勋
            waitList.add(monitor);
        }
        // 自旋 防止假唤醒
        while (isLockedForThisThread) {
            System.out.println("---自旋锁---"+Thread.currentThread().getName());
            synchronized (this) {
                isLockedForThisThread = (isLock || waitList.get(0) != monitor);
                if (!isLockedForThisThread) {
                    // isLockedForThisThread =false
                    // 当前无对象拿到锁 && monitor就是waitList.get()
                    // 拿到锁的条件 当前无monitor拿到锁并且当前monitor(线程)就是下一个线程
                    isLock = true;
                    waitList.remove(monitor);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            try {
                //  isLockedForThisThread =true
                // 有线程已经拿到了锁
                // 或 当前线程并非是下一个
                monitor.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitList.remove(monitor);
                }
                throw e;
            }
        }

    }

    /**
     * 释放锁
     * 唤醒第一个
     */
    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLock = false;
        lockingThread = null;
        //总是取第一个元素（监控对象）解锁
        if (waitList.size() > 0) {
            waitList.get(0).doNotify();
        }
    }

}
