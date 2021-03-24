package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   锁
 * @ClassName WorkDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/21 15:08
 * @Version 1.0
 **/
public class WorkDemo1 extends Thread {
    private static int index=0;
    @Override
    public void run() {
        synchronized (WorkDemo1.class){
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
        Thread thread1=new WorkDemo1();
        Thread thread2=new WorkDemo1();
        thread1.start();
        thread2.start();
    }
}
