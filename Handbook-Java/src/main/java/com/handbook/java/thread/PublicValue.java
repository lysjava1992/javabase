package com.handbook.java.thread;

/**
 * 同步方法
 */
public class PublicValue {

    public String username = "default";
    public String password = "default";

    /**
     * 静态方法锁，类锁锁的是PublicValue.class
     * @throws InterruptedException
     */
    public synchronized static void fun() throws InterruptedException {
        System.out.println("静态方法锁开始");
        Thread.sleep(10000);
        System.out.println("静态方法锁结束");
    }

    /**
     * 方法锁，锁的是对象
     */
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

    //非同步方法，可以不加锁
    public  void getValue() {
        System.out.println("method=getValue " + "\t" +  "threadName="
                + Thread.currentThread().getName()+ "\t" + " username=" + username
                + ", password=" + password);
    }

    public static void main(String[] args) {
        PublicValue pv=new PublicValue();
        Thread thread=new Thread(()->{
            try {
                PublicValue.fun();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        Thread thread2=new Thread(()->{
            pv.setValue("Tom","123456");
        });
        Thread thread3=new Thread(()->{
            pv.getValue();
        });
        thread2.start();
        thread3.start();

    }

}
