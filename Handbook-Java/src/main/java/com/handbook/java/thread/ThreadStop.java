package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ThreadStop
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/28 10:07
 * @Version 1.0
 **/
public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {
        ThreadDemo demo=new ThreadDemo();
        Thread thread=new Thread(demo);
        thread.start();
        Thread.sleep(1000);
        // 强制停止
        //thread.stop();

        //中断异常 可以捕捉SecurityException
        thread.interrupt();
    }
   static class ThreadDemo implements Runnable{
        private  int a=0;
        private int b=0;
       @Override
       public void run() {
           synchronized (this){
               a++;
               try {
                   Thread.sleep(5000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               b++;
           }
       }
   }
}
