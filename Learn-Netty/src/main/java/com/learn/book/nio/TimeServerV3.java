package com.learn.book.nio;

public class TimeServerV3 {
    public static void main(String[] args) {
         MultiplexerTimeServer timServer=new MultiplexerTimeServer(8011);
         new Thread(timServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
