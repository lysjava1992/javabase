package com.handbook.java.concurrent.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @description:
 *       java 中线程的创建有4中方式
 *       1.继承Thread类，重写run方法                   【无返回值】
 *       2.实现Runnable接口，重写run方法              【无返回值】
 *       3.实现Callable接口，重写call方法             【有返回值】
 *
 * @author: Mr.Luan
 * @create: 2019-11-06 17:04
 **/
public class CreateThread {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
         CreateOne createOne=new CreateOne();
         createOne.start();

         Thread thread=new Thread(new CreateTow());
         thread.start();

        CreateThree createThree=new CreateThree();
        FutureTask<String> futureTask = new FutureTask<String>(createThree);
        Thread threadThree = new Thread(futureTask);
        threadThree.start();  //开启线程
        String  result = futureTask.get();
        System.out.println(result);

    }

    /**
     * 每次创建一个新的线程，都要新建一个Thread子类的对象
     * 启动线程，new Thread子类（）.start（）
     * 创建线程实际调用的是父类Thread空参的构造器
     */
    static class CreateOne extends Thread{
        @Override
        public void run() {
           System.out.println("extends Thread 实现的线程");
        }
    }


    /**
     * 不论创建多少个线程，只需要创建一个Runnable接口实现类的对象
     * 启动线程，new Thread（Runnable接口实现类的对象）.start()
     * 创建线程调用的是Thread类Runable类型参数的构造器
     */
    static class CreateTow implements Runnable{

        @Override
        public void run() {
            System.out.println("implements Runnable 实现的线程");
        }
    }

    /**
     *  自定义类实现Callable接口时，必须指定泛型，该泛型即返回值的类型
     *  每次创建一个新的线程，都要创建一个新的Callable接口的实现类
     *
     *  Thread    来启动线程
     *  FutureTask 来接收返回值
     */
    static class  CreateThree implements Callable<String>{
        @Override
        public String call() throws Exception {
            return "implements Callable 实现的线程";
        }
    }
}
