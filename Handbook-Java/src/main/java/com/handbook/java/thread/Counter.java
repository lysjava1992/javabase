package com.handbook.java.thread;


import sun.misc.Lock;

public class Counter {
    private int count = 0;

    public int inc() {
        try {
            count++;
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

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

    private Lock lock = new Lock();

    public int incLock() {
        try {
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
        lock.unlock();
        return count;

    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(counter.incLock());
            }).start();
        }
    }
}
