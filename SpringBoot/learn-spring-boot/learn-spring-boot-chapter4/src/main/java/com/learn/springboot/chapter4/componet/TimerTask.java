package com.learn.springboot.chapter4.componet;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description:
 *
 *   fixedRate   任务开始时间间隔
 *   fixedDelay  任务执行完成间隔
 *   initialDelay  首次启动延迟时间
 *
 *   支持cron表达式
 * @author: Mr.Luan
 * @create: 2020-03-26 15:24
 **/
@Component
public class TimerTask {
    //@Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate>>>"+new Date());
    }
    //@Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay>>>"+new Date());
    }
    //@Scheduled(initialDelay = 2000,fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay>>>"+new Date());
    }
    @Scheduled(cron = "0/3 * * * * *")
    public void cron() throws InterruptedException {


        System.out.println("cron>>>"+new Date());
        Thread.sleep(5000);
    }
}
