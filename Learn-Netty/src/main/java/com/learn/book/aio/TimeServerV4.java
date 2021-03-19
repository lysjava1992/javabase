package com.learn.book.aio;

public class TimeServerV4 {
    public static void main(String[] args) {
        AsyncTimeServerHandler timeServer=new AsyncTimeServerHandler(8011);
        new Thread(timeServer,"AIO-AsyncTimeServerHandler-001").start();
    }
}
