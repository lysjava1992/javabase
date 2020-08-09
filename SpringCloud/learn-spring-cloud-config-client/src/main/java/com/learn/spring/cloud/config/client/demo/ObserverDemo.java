package com.learn.spring.cloud.config.client.demo;

import java.util.Observable;
import java.util.Observer;

/**
 *
 *观察者模式
 * @ClassName ObserverDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/8/9 14:14
 * @Version 1.0
 **/
public class ObserverDemo {
    public static void main(String[] args) {
        //发布
         CustomObservable observable=new CustomObservable();
        //增加订阅者
        observable.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.err.println(arg);
            }
        });
        observable.setChanged();
        observable.notifyObservers("Hello");
    }
    public static class CustomObservable extends Observable{
        @Override
        public void setChanged() {
            super.setChanged();
        }
    }
}
















