package com.learn.test;

import com.learn.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class HelloTest {
    public static void main(String[] args) {
        try {
            // 创建调度器
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.getContext().put("skey", "svalue");

            //创建触发器
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .usingJobData("t1", "tv1")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();
            trigger.getJobDataMap().put("t2", "tv2");

            //创建job
            JobDetail job = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("myjob", "mygroup")
                    .usingJobData("j1", "jv1").build();
            job.getJobDataMap().put("j2", "jv2");


            //注册trigger启动scheduler
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
