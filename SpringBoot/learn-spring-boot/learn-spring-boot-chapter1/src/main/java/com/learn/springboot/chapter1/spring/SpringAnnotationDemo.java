package com.learn.springboot.chapter1.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SpringAnnotationDemo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/21 15:29
 * @Version 1.0
 **/
public class SpringAnnotationDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        context.register(SpringAnnotationDemo.class);
        //上下文启动
        context.refresh();
        System.out.println(context.getBean("springAnnotationDemo"));
    }
}
