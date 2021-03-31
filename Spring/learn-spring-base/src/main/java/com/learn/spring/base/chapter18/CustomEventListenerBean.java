package com.learn.spring.base.chapter18;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListenerBean implements ApplicationListener<CustomEvent> {


    public void onApplicationEvent(CustomEvent event) {
        System.out.println("CustomEventListenerBean :"+event.getClass());
    }
}
