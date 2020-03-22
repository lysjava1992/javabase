package com.learn.springboot.chapter1.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

/**
 *   事件类型 ApplicationEvent
 *   事件监听 ApplicationListener
 *   事件广播 ApplicationEventMulticaster
 *
 * @ClassName SpringEventListenerDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/21 15:31
 * @Version 1.0
 **/
public class SpringEventListenerDemo {
    public static void main(String[] args) {
        GenericApplicationContext context=new GenericApplicationContext();
        //添加监听事件
//        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
//            @Override
//            public void onApplicationEvent(ApplicationEvent event) {
//                System.err.println("监听事件："+event);
//            }
//        });
         context.addApplicationListener(new ClosedListener());

       //ContextRefreshedEvent事件
        context.refresh();

        //发布事件
        //PayloadApplicationEvent事件
        context.publishEvent("HelloWorld!!");
        //SpringEventListenerDemo$MyEvent
        context.publishEvent(new MyEvent("HelloWord 2020"));

        //ContextClosedEvent事件
        context.close();
    }
    private static class  ClosedListener implements ApplicationListener<ContextClosedEvent>{

        @Override
        public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
            System.out.println("上下文关闭："+contextClosedEvent);
        }
    }
    private static class MyEvent extends ApplicationEvent{

        public MyEvent(Object source) {
            super(source);
        }
    }
}
