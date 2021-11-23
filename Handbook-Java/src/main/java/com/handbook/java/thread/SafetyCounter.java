package com.handbook.java.thread;


import sun.misc.Lock;

/**
 * 实现一个线程安全的计数
 */
public class SafetyCounter {
    private int count = 0;

    /**
     * 非线程安全
     * @return
     */
    public int inc() {
        try {
            count++;
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    /**
     * 通过synchronized方法块
     *  锁定的是this 即对象
     * @return
     */
    public int incSynchronized() {
        synchronized (this) {
            try {
                count++;
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return count;
        }
    }

    /**
     *  synchronized无法判断是否获取锁的状态，
     *  Lock可以判断是否获取到锁；
     *
     *  synchronized的锁可重入、不可中断、非公平，
     *  而Lock锁可重入、可判断、可公平（两者皆可）
     *
     *  Lock锁适合大量同步的代码的同步问题，
     *  synchronized锁适合代码少量的同步问题。
     */
    private Lock lock = new Lock();
    public int incLock() {
        try {
            // 拿锁
            lock.lock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            count++;
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //需要手动释放锁
        lock.unlock();
        return count;

    }

    public static void main(String[] args) {
        SafetyCounter safetyCounter = new SafetyCounter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(safetyCounter.incLock());
            }).start();
        }
    }
}
