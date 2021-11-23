package com.handbook.java.thread;

/**
 * 对象锁
 */
public class SynchronizedObject {
    public void doSome(Object lock){
        // 此时锁住的是lock
        // 即多个线程若是同一个lock对象则只能一个线程执行
        synchronized (lock){
            while (true){
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedObject synchronizedObject =new SynchronizedObject();
        SynchronizedObject synchronizedObject2 =new SynchronizedObject();
        Object lock=new Object();

        Thread thread=new Thread(()->{
            synchronizedObject.doSome(lock);
        },"Thread-0");
        Thread thread2=new Thread(()->{
            synchronizedObject2.doSome(lock);
        },"Thread-1");
        thread.start();
        thread2.start();
    }
}
