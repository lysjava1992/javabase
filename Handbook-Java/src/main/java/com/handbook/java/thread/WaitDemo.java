package com.handbook.java.thread;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName WaitDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/3/21 16:12
 * @Version 1.0
 **/
public class WaitDemo {
    public static void main(String[] args) throws InterruptedException {
     new WaitDemo().test();
     char a='字';
    }
    public synchronized  void test() throws InterruptedException {
        System.out.println("start "+Thread.currentThread().getName());
        wait();
        System.out.println("end");
    }
}
