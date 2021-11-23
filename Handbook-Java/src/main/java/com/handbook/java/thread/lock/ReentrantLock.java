package com.handbook.java.thread.lock;

/**
 * 可重入锁
 *   outer()和inner()  这两个方法（代码块）都由同一个管程对象（”this”)所同步。
 *   如果一个线程已经拥有了一个管程对象上的锁，
 *   那么它就有权访问被这个管程对象同步的所有代码块。这就是可重入。
 *   线程可以进入任何一个它已经拥有的锁所同步着的代码块
 *
 *  一个对象只有一个锁
 *  synchronized方法也成对象锁
 *  锁的是对象
 *
 *  因此，虽然是两个 synchronized方法，但使用的是同一把锁
 */
public class ReentrantLock {
    public synchronized void outer() {
        System.out.println("------outer------");
        inner();
    }

    public synchronized void inner() {
        System.out.println("------inner------");
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock =new ReentrantLock();
        new Thread(()->{
            reentrantLock.outer();
        }).start();
    }
}
