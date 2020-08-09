package com.learn.spring.cloud.config.client.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SpringEventListenerDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/8/9 14:27
 * @Version 1.0
 **/
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        //上下文
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //注册事件
        context.addApplicationListener(new ApplicationListener<MyApplicationEvent>() {
            //触发事件
            @Override
            public void onApplicationEvent(MyApplicationEvent applicationEvent) {
                System.err.println(applicationEvent.getSource());
            }
        });
        context.refresh();
        //发布事件
        context.publishEvent(new MyApplicationEvent("Hello SpringCloud"));
    }
    private static class MyApplicationEvent extends ApplicationEvent{

        public MyApplicationEvent(Object source) {
            super(source);
        }
    }

}
