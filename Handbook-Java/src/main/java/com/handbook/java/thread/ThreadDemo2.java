package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ThreadDemo2
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/20 16:01
 * @Version 1.0
 **/
public class ThreadDemo2 extends Thread{
    private static int index;

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" :"+(index++));
        }

    }

    public static void main(String[] args) {
        for (int i = 0; i <500 ; i++) {
            new ThreadDemo2().start();
        }
    }
}
