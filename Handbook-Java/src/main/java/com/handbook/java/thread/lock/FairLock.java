package com.handbook.java.thread.lock;

import com.handbook.java.thread.lock.QueueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 公平锁
 */
public class FairLock {
    private boolean isLocked = false;
    private Thread lockingThread = null;
    private List<QueueObject> waitingThreads =
            new ArrayList<QueueObject>();

    /**
     *  每个调用lock()的线程都会进入一个队列
     *    当解锁后，只有队列里的第一个线程被允许锁住FairLock实例
     *    所有其它的线程都将处于等待状态，直到它们处于队列头部
     * @throws InterruptedException
     */
    public void lock() throws InterruptedException {
        //每次调用lock,就会新建一个临时对象标记当前线程
        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            //不管是否获得锁，先将监视器压入队列
            waitingThreads.add(queueObject);
        }
        while (isLockedForThisThread) {
            synchronized (this) {
                isLockedForThisThread =( isLocked || waitingThreads.get(0) != queueObject);
                /**
                 *  当 isLockedForThisThread ==false时，当前线程获取FairLock实例的锁：
                 *   1. 没有其它线程占用FairLock对象（isLock==false）
                 *   2. 当前线程的监听器处在队列头部（主要是为了拦截FairLock未被占有的时候乱入的不是最先的线程）
                 *       如果当前线程不再头部，则主动wait()
                 *
                 */
                if (!isLockedForThisThread) {
                    isLocked = true;
                    waitingThreads.remove(queueObject);
                    lockingThread = Thread.currentThread();
                    return;
                }
            }
            try {
                queueObject.doWait();
            } catch (InterruptedException e) {
                synchronized (this) {
                    waitingThreads.remove(queueObject);
                }
                throw e;
            }
        }
    }

    public synchronized void unlock() {
        if (this.lockingThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    "Calling thread has not locked this lock");
        }
        isLocked = false;
        lockingThread = null;
        if (waitingThreads.size() > 0) {
            waitingThreads.get(0).doNotify();
        }
    }


}
