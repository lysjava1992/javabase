package com.handbook.java.thread.lock;


import sun.misc.Lock;

/**
 *非可重入锁
 */
public class NoReentrantLock {
    Lock lock = new Lock();

    /**
     *  1.调用outer()的线程首先会锁住Lock实例，然后继续调用inner()。
     *  2.inner()方法中该线程将再一次尝试锁住Lock实例，结果该动作会失败（也就是说该线程会被阻塞），
     *  3.因为这个Lock实例已经在outer()方法中被锁住了。
     *    从而造成死锁
     * @throws Exception
     */
    public void outer()throws Exception{
        lock.lock();
        inner();
        lock.unlock();
    }

    public void  inner()throws Exception{
        lock.lock();
       System.out.println("-------------------");
        lock.unlock();
    }

    public static void main(String[] args) {
        NoReentrantLock reentrant=new NoReentrantLock();
           new Thread(()->{
               try {
                   reentrant.outer();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }).start();
    }
}
