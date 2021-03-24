package com.handbook.java.thread;

public class DoSomeThing {
    public void doSome(Object lock){
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
        DoSomeThing doSomeThing=new DoSomeThing();
        DoSomeThing doSomeThing2=new DoSomeThing();
        Object lock=new Object();
      //  String lock=new String("A");
        Thread thread=new Thread(()->{
            doSomeThing.doSome(lock);
        },"Thread-0");
        Thread thread2=new Thread(()->{
            doSomeThing2.doSome(lock);
        },"Thread-1");
        thread.start();
        thread2.start();
    }
}
