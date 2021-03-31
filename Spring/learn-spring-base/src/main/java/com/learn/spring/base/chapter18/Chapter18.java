package com.learn.spring.base.chapter18;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Chapter18 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(JavaSpringConfigure.class);
        EventService eventService=context.getBean(EventService.class);
        eventService.doSome();
        context.registerShutdownHook();
    }
}
