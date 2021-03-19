package com.learn.task;

import lombok.SneakyThrows;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest extends TimerTask {
    private String jobName ;

    public TimerTest(String jobName) {
        super();
        this.jobName = jobName;
    }

    /**
     * 轮询周期 3S   >= 执行耗时 1S 则 执行间隔为3
     * 轮询周期 3S  <=执行周期 10S  则执行间隔为10S
     *
     */
    @SneakyThrows
    @Override
    public void run() {
       Thread.sleep(10000);
        System.out.println("execute:  " + LocalDateTime.now());
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        //delay 延迟执行时间
        //period 周期
        long delay = 1000;
        long period = 3000;
     // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        timer.schedule(new TimerTest("job1"), delay, period);

    }
}
