package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *    Class锁
 *    类锁，多线程下即使是不同的实例
 *    但依然是拿到的同一把锁
 * @Author Mr.Luan
 **/
public class SynchronizedClass extends Thread {
    private static int index=0;
    @Override
    public void run() {
        //类锁
        synchronized (SynchronizedClass.class){
            try {
                Thread.sleep(50);
                {
                    for(int j = 1; j <= 5; j++)
                    {
                        System.out.print("线程ID:"+Thread.currentThread().getId()+"打印"+index++ +"\n");
                    }

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread thread1=new SynchronizedClass();
        Thread thread2=new SynchronizedClass();
        thread1.start();
        thread2.start();
    }
}
