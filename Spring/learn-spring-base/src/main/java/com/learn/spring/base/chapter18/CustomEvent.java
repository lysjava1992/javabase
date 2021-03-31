package com.learn.spring.base.chapter18;
import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    private final String msg;


    public CustomEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

}
