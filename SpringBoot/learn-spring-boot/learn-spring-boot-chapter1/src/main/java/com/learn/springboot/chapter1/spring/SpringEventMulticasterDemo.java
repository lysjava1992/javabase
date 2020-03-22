package com.learn.springboot.chapter1.spring;

import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SpringEventMulticasterDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/21 15:55
 * @Version 1.0
 **/
public class SpringEventMulticasterDemo {
    public static void main(String[] args) {
        ApplicationEventMulticaster multicaster=new SimpleApplicationEventMulticaster();
        multicaster.addApplicationListener(applicationEvent ->
        {System.out.println("接收到事件："+ applicationEvent);});
        multicaster.multicastEvent(
                new PayloadApplicationEvent<Object>("","HelloWorld"));

    }
}
