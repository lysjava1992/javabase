package com.learn.springboot.chapter1;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SpringBootEventApplication
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/21 16:03
 * @Version 1.0
 **/
@EnableAutoConfiguration
public class SpringBootEventApplication {
    public static void main(String[] args) {
        /**
         * ApplicationContextInitializedEvent
         *
         * ApplicationPreparedEvent
         *
         * ContextRefreshedEvent
         *
         * ServletWebServerInitializedEvent
         *
         * ApplicationStartedEvent
         *
         * ApplicationReadyEvent
         *
         * ContextClosedEvent
         *
         * ApplicationFailedEvent
         */
        new SpringApplicationBuilder(SpringBootEventApplication.class)
                 .listeners(applicationEvent -> {
                     System.err.println("【事件监听】： "+applicationEvent.getClass().getName());
                 })
                .run(args)
                .close();
    }
}





