package com.handbook.java.thread;

import org.junit.Test;

import java.util.Random;

public class LockTest {
    private int value;
    private TestLock lock = new TestLock();

    public int add(int n) throws InterruptedException {
        lock.lock();
        this.value = n;
        Thread.sleep(2);
        lock.unlock();
        return ++this.value;
    }

    public static void main(String[] args) {
        LockTest test = new LockTest();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int value = random.nextInt();
                try {
                    System.out.println(Thread.currentThread().getName()+"  -   "+value + "   -    " + test.add(value));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
