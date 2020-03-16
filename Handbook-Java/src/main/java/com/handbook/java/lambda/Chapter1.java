package com.handbook.java.lambda;

/**
 * @description:
 *   OOP 面向对象     抽象数据
 *   FP  函数式编程   抽象行为   通过合并现有代码生成新功能
 * @author: Mr.Luan
 * @create: 2019-12-10 09:10
 **/
public class Chapter1 {
    public static void main(String[] args) {
                 Thread thread=new Thread(new Runnable() {
                     @Override
                     public void run() {
                         System.out.println("Thread=====================");
                     }
                 });
                 thread.start();

                 Thread thread1=new Thread(()->System.out.println("Lambda==============="));
                 thread1.start();

                 Thread thread2=new Thread(()->{for (int i=0;i<10;i++){
                     System.out.println("Lambda==============="+i);
                 }});
                 thread2.start();
    }
}
