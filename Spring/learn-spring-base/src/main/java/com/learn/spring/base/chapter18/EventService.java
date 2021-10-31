package com.learn.spring.base.chapter18;


import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher=applicationEventPublisher;


    }
   public void   doSome(){
        System.out.println("-----执行-----");
       publisher.publishEvent(new CustomEvent(this,"自定义事件"));
    }

    @EventListener
    @Order(5)
    private void log(CustomEvent customEvent){
      System.out.println("doSome被调用");
    }


    @EventListener
    @Order(1)
    private void modification(CustomEvent customEvent){
        System.out.println("doSome被调用，修改状态");
    }

    @EventListener
    @Async
    void free(CustomEvent customEvent){
        System.out.println("doSome被调用，异步修改");
    }
}
