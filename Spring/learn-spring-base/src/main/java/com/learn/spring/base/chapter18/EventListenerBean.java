package com.learn.spring.base.chapter18;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EventListenerBean implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("EventListenerBean :"+event.getClass());
    }
}
