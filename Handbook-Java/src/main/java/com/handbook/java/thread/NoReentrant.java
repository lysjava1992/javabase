package com.handbook.java.thread;


import sun.misc.Lock;

/**
 *
 */
public class NoReentrant {
    Lock lock = new Lock();

    public void outer()throws Exception{
        lock.lock();
        //调用outer()的线程首先会锁住Lock实例，然后继续调用inner()。
        // inner()方法中该线程将再一次尝试锁住Lock实例，结果该动作会失败（也就是说该线程会被阻塞），
        // 因为这个Lock实例已经在outer()方法中被锁住了。
        inner();
        lock.unlock();
    }

    public void  inner()throws Exception{
        lock.lock();
       System.out.println("-------------------");
        lock.unlock();
    }

    public static void main(String[] args) {
        NoReentrant reentrant=new NoReentrant();
           new Thread(()->{
               try {
                   reentrant.outer();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }).start();
    }
}
