package com.learn.springboot.chapter5.task;

import com.learn.springboot.chapter5.task.model.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class CustomSchedulingConfigurer implements SchedulingConfigurer {
    //自增ID
    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    //调度器
    private ThreadPoolTaskScheduler taskScheduler;

    //维护 CronTask 和 ScheduledFuture
    private Map<CronTask, ScheduledFuture> cronTaskScheduledFutureMap;

    //任务列表
    private List<Task> taskList;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskScheduler = taskScheduler();
        cronTaskScheduledFutureMap = new HashMap<>();
        taskList = new ArrayList<>();
        //getCronTaskList() 获取Spring托管的所有cron定时任务
        taskRegistrar.getCronTaskList()
                .forEach(
                        cronTask -> {
                            // 手动使用线程池调度器调度任务，并保存每一个任务调度的scheduledFuture，
                            // 在对Future的实现类scheduledFuture中可以实现对任务的取消
                           ScheduledFuture scheduledFuture = taskScheduler.schedule(cronTask.getRunnable(),cronTask.getTrigger());

                           cronTaskScheduledFutureMap.put(cronTask,scheduledFuture);
                            ScheduledMethodRunnable scheduledMethodRunnable= (ScheduledMethodRunnable) cronTask.getRunnable();

                            taskList.add(new Task(ATOMIC_INTEGER.getAndIncrement(),
                                                  scheduledMethodRunnable.getMethod().toGenericString(),
                                                  cronTask.getExpression(),
                                                  Task.State.WAITTING_NEXT,
                                                  cronTask.getTrigger().nextExecutionTime(new SimpleTriggerContext())));
                        }
                );
            //取消Spring托管执行
            taskRegistrar.setCronTasksList(null);

    }
 public  List<Task> getTaskList(){
        return taskList;
 }
 public void editTask(Task task){
        if(taskScheduler==null||cronTaskScheduledFutureMap==null)return;
        cronTaskScheduledFutureMap.forEach(((cronTask, scheduledFuture) -> {
            if(cronTask.getRunnable() instanceof ScheduledMethodRunnable){
                ScheduledMethodRunnable scheduledMethodRunnable= (ScheduledMethodRunnable) cronTask.getRunnable();
                String methodName=scheduledMethodRunnable.getMethod().toGenericString();
                System.out.println(task.getTriggerName().equals(methodName));
                if(task.getTriggerName().equals(methodName)){
                    switch (task.getState()){
                        case RUN:
                            cronTaskScheduledFutureMap.put(cronTask, taskScheduler.schedule(scheduledMethodRunnable, new CronTrigger(task.getCron())));
                            break;
                        case STOP:
                            scheduledFuture.cancel(true);
                            break;
                    }
                }
            }
        }));
 }
   @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        //创建一个线程池调度器
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(20);
        scheduler.setThreadNamePrefix("task-");
        //设置是否在关机时等待计划任务完成.默认false不等待，直接中断当前任务
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        //设置这个执行程序应该阻塞的最大秒数
        //等待剩余的任务完成它们的执行在容器的其他部分继续关闭之前
        scheduler.setAwaitTerminationMillis(60);
        //设置当任务被取消的同时从当前调度器移除的策略
        scheduler.setRemoveOnCancelPolicy(true);
        return scheduler;
    }
}
