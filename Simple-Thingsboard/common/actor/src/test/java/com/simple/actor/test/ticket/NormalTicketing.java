package com.simple.actor.test.ticket;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicInteger;

public class NormalTicketing implements Runnable{
   private AtomicInteger tickets=new AtomicInteger(10);
    @SneakyThrows
    @Override
    public void run() {
         while (tickets.get()>0){
              System.out.println(Thread.currentThread().getName()+" 出售第"+(10-tickets.get()+1) +"张");
              Thread.sleep(1);
              tickets.getAndDecrement();
         }
    }

    public static void main(String[] args) {
        NormalTicketing normalWicket=new NormalTicketing();
        Thread wicket1= new Thread(normalWicket,"窗口1");
        Thread wicket2=  new Thread(normalWicket,"窗口2");
        wicket1.start();
        wicket2.start();

    }
}
