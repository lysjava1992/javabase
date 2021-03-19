package com.learn.test;

import com.learn.job.HelloJob2;
import com.learn.listener.CustomListener;
import lombok.SneakyThrows;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
public class HelloTest2 {
    @SneakyThrows
    public static void main(String[] args) {
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail= JobBuilder.newJob(HelloJob2.class)
                             .withIdentity("Job-Name","Job-group")
                             .usingJobData("count",0)
                             .storeDurably(true) //指明任务就算没有绑定Trigger仍保留在Quartz的JobStore中,
                             .build();

          // 此接口中的JobDetail的durable必须为true
        scheduler.addJob(jobDetail, false);

        Trigger trigger= TriggerBuilder.newTrigger()
                         .withIdentity("Trigger-Name","Trigger-Group")
                         .usingJobData("name","Trigger-Name")
                         .withSchedule(CronScheduleBuilder.cronSchedule("50 28 * * * ? "))
                        // .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                         .forJob(jobDetail)
                          .withPriority(500)
                         .build();

        scheduler.scheduleJob(trigger);
        Trigger trigger2= TriggerBuilder.newTrigger()
                .withIdentity("Trigger-Name-2","Trigger-Group")
                .usingJobData("name","Trigger-Name-2")
               // .withSchedule(CronScheduleBuilder.cronSchedule("50 28 * * * ? "))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever())
                .forJob(jobDetail)
                .withPriority(5000)
                .build();
        scheduler.scheduleJob(trigger2);
        scheduler.getListenerManager().addTriggerListener(new CustomListener());

        scheduler.start();
    }
}
