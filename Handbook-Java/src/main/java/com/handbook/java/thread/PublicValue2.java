package com.handbook.java.thread;

import jdk.nashorn.internal.ir.Block;

/**
 *  方法块
 */
public class PublicValue2 {
    public String username = "default";
    public String password = "default";
    private Object lock=new Object();

    public synchronized void setValue(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("method=setValue " +"\t" + "threadName="
                    + Thread.currentThread().getName() + "\t" + "username="
                    + username + ", password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void  setValue2(String username, String password) throws Exception{
        synchronized (lock){
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("method=setValue " +"\t" + "threadName="
                    + Thread.currentThread().getName() + "\t" + "username="
                    + username + ", password=" + password);
        }
    }
    public  void  getValue()throws Exception {
        synchronized (this){
            System.out.println("method=getValue " + "\t" +  "threadName="
                    + Thread.currentThread().getName()+ "\t" + " username=" + username
                    + ", password=" + password);
        }

    }
    public static void main(String[] args) {
        PublicValue2 pv=new PublicValue2();
        Thread thread=new Thread(()->{
        pv.setValue("Kom","123456");
        });
        Thread thread2=new Thread(()->{
            try {
                pv.setValue2("Kom","123456");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        Thread thread1=new Thread(()->{
            try {
                pv.getValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread2.start();
        thread1.start();
    }
}
