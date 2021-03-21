package com.handbook.java.thread;

/**
 * 线程创建
 *   2种方式
 *     继承
 *     实现
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ThreadDamo1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 15:32
 * @Version 1.0
 **/
public class ThreadDemo1 {
    public static void main(String[] args) {
       Thread thread1= new ThreadOne();
       thread1.setName("继承1");
       thread1.start();

       Thread thread2=new Thread("继承2"){
           @Override
           public void run() {
               System.out.println("线程名称:"+this.getName());
           }
       };
       thread2.start();


       Thread thread3=new Thread(new ThreadTwo());
       thread3.setName("实现1");
       thread3.start();

       Thread thread4=new Thread(() -> {
           System.out.println("线程名称:"+Thread.currentThread().getName());
       },"实现2");
     thread4.start();
    }
    static class ThreadOne extends Thread{
        @Override
        public void run() {
            System.out.println("线程名称:"+this.getName());
        }
    }
   static class ThreadTwo implements Runnable{

        @Override
        public void run() {
            System.out.println("线程名称:"+Thread.currentThread().getName());
        }
    }
}
