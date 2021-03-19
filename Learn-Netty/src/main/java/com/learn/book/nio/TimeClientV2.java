package com.learn.book.nio;

public class TimeClientV2 {
    public static void main(String[] args) {
    new  Thread(new TimeClientHandle("127.0.0.1",8011),"TimeClient-001").start();
    }
}
