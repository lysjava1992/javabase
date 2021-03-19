package com.learn.springboot.chapter4.componet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;


@Configuration
public abstract class CustomScheduling implements SchedulingConfigurer {
    /**
     * 定时任务周期表达式
     */
    private String cron;

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        //设置任务注册器的调度器
        registrar.setScheduler(taskScheduler());
        registrar.addTriggerTask(
                //定时任务要执行的方法
                () -> {
                    processTask();
                },
                //调度实现的时间控制
                triggerContext -> {
                    if (StringUtils.isEmpty(cron)) {
                        cron = getCron();
                    }
                    CronTrigger trigger = new CronTrigger(cron);
                    return trigger.nextExecutionTime(triggerContext);
                }
        );

    }

    /**
     * 用于注册计划任务
     * 每一个被调度的任务都会由线程池中一个线程去执行，
     * 因此任务是并发执行的，相互之间不会受到干扰。
     *  需要注意的是，只有当任务的执行时间到来时，
     *  ScheduedExecutor 才会真正启动一个线程，
     *  其余时间 ScheduledExecutor 都是在轮询任务的状态
     *    @return
     */
    @Bean()
    public Executor taskScheduler() {
        ThreadFactory factory = new CustomizableThreadFactory("TaskScheduler");
        //创建线程池
        //当调度器shutdown被调用时等待当前被调度的任务完成

        return Executors.newScheduledThreadPool(5, factory);
    }
    /**
     *  任务的处理函数
     * 本函数需要由派生类根据业务逻辑来实现
     */
    protected abstract void processTask();
    /**
     * @return String
     * 获取定时任务周期表达式
     * 本函数由派生类实现，从配置文件，数据库等方式获取参数值
     */
    protected abstract String getCron();
}
