package com.learn.springboot.chapter5.task;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
@EnableScheduling
@Component
public class TaskWork {
//    @SneakyThrows
//    @Scheduled(cron = "0/3 * * * * *")
//    public void cron1() {
//        System.out.println("任务一执行 "+ LocalDateTime.now());
//        Thread.sleep(7000);
//    }
    @SneakyThrows
    @Scheduled(cron = "0/10 * * * * *")
    public void cron2() {
        System.out.println("任务二执行 "+ LocalDateTime.now());
        Thread.sleep(15000);
    }
//    @Scheduled(cron = "0/9 * * * * *")
//    public void cron3() {
//        System.out.println("任务三执行 "+ LocalDateTime.now());
//    }
}
