package com.handbook.java.thread;

import java.util.Random;

public class TestFairLockTest {
    private int value;
    private FairLockTest lock = new FairLockTest();

    public int add(int n) throws Exception {
        lock.lock();
        this.value = n;
        Thread.sleep(10);
        lock.unlock();
        return ++this.value;
    }
    public  void test() throws InterruptedException {
        while (true){
            System.out.println("-------------"+Thread.currentThread().getName());
            Thread.sleep(5000);
        }
    }
    public static void main(String[] args)throws Exception {
        TestFairLockTest test = new TestFairLockTest();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                int value = random.nextInt();
                try {
                    test.test();
                   // System.out.println(Thread.currentThread().getName()+"  -   "+value + "   -    " + test.add(value));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
