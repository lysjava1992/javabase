package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MySignal
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/21 15:29
 * @Version 1.0
 **/
public class MySignal {
    protected boolean hasDataToProcess = false;

    public  synchronized boolean hasDataToProcess() throws InterruptedException {
        System.out.println("----------get start---------");
        Thread.sleep(5000);
        System.out.println("----------get end---------");
        return this.hasDataToProcess;
    }

    public synchronized void  setHasDataToProcess(boolean hasData) throws InterruptedException {
        System.out.println("----------set start---------");
        Thread.sleep(5000);
        System.out.println("----------set end---------");
        this.hasDataToProcess = hasData;
    }
    /**
     * hasDataToProcess() 和 setHasDataToProcess（）
     * 1. 都不加synchronized可以通知进入执行
     * 2. 其中一个加synchronized则该方法同时只有一个线程执行，另一个方法可以同时执行
     * 3. 两个都加synchronized，则同一时刻只有一个线程一个方法执行
     *
     **/
    public static void main(String[] args) {
        MySignal mySignal=new MySignal();
        Thread thread=new Thread(() ->{
            try {
                mySignal.setHasDataToProcess(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2=new Thread(() ->{
            try {
                System.out.println(mySignal.hasDataToProcess());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3=new Thread(() ->{
            try {
                mySignal.setHasDataToProcess(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread4=new Thread(() ->{
            try {
                System.out.println(mySignal.hasDataToProcess());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread.start();
        thread3.start();
        thread4.start();

    }

}
