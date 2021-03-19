package com.learn.book.aio;

public class TimeClientV3 {
    public static void main(String[] args) {
        new Thread(new AsyncTimeClientHandler("127.0.0.1",8011),"AIO-AsyncTimeClientHandler-001").start();
    }
}
