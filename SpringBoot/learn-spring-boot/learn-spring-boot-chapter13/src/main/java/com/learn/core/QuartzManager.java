package com.learn.core;

import com.learn.entity.SysQuartz;
import com.learn.listener.CustomJobListener;
import com.learn.listener.CustomTriggerListener;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.spi.JobFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
@Slf4j
public class QuartzManager {
    private static CustomJobFactory jobFactory = new CustomJobFactory();
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    static {
        JobListener myJobListener = new CustomJobListener();
        TriggerListener myTriggerListener=new CustomTriggerListener();
        try {
            schedulerFactory.getScheduler().getListenerManager().addTriggerListener(myTriggerListener);
            schedulerFactory.getScheduler().getListenerManager().addJobListener(myJobListener);
        } catch (SchedulerException e) {
            log.error("quartz manager listener init error",e);
        }
    }

    public QuartzManager(CustomJobFactory jobFactory) {
        this.jobFactory = jobFactory;
    }
    public static JobFactory getJobFactory(){
        return jobFactory;
    }
    public static SchedulerFactory getSchedulerFactory(){
        return schedulerFactory;
    }

    /**
     *  手动触发
     * @param jobName
     * @param jobGroupName
     * @param params
     */
    public static void runJob(String jobName, String jobGroupName, Map<String,Object> params){
        try{
            Scheduler scheduler = schedulerFactory.getScheduler();
            JobDataMap jobDataMap=new JobDataMap();
            jobDataMap.put("quartz_passFlagForQuartzJob", "true");
            jobDataMap.putAll(params);
            scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName),jobDataMap);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    /**
     *  手动触发
     * @param jobName
     * @param jobGroupName
     */
    public static boolean runJob(String jobName, String jobGroupName){
        try{
            Scheduler scheduler= schedulerFactory.getScheduler();
            JobDataMap jobDataMap=new JobDataMap();
            scheduler.triggerJob(JobKey.jobKey(jobName, jobGroupName),jobDataMap);
            return true;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     *  添加一个定时任务
     *
     * @param jobName 任务名
     * @param jobGroupName 任务组名
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     *  @param className 任务类
     * @param cron 时间设置，参考quartz说明文档
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void addJob(String jobName, String jobGroupName, String triggerName,
                              String triggerGroupName, String className, String cron) {
        try {
            Class jobClass = Class.forName(className);
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
            triggerBuilder.withIdentity(triggerName, triggerGroupName);
            triggerBuilder.startNow();
            triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            CronTrigger trigger = (CronTrigger) triggerBuilder.build();
            scheduler.scheduleJob(jobDetail, trigger);
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  修改任务
     * @param jobName 任务名称
     * @param jobGroupName 任务组
     * @param triggerName  触发器名称
     * @param triggerGroupName 触发器组
     * @param cron  cron
     */
    public static void modifyJobTime(String jobName, String jobGroupName, String triggerName,
                                     String triggerGroupName, String cron) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                /** 方式一 ：调用 rescheduleJob 开始 */
                TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
                triggerBuilder.withIdentity(triggerName, triggerGroupName);
                triggerBuilder.startNow();
                triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
                trigger = (CronTrigger) triggerBuilder.build();
                scheduler.rescheduleJob(triggerKey, trigger);



                /** 方式二：先删除，然后在创建一个新的Job */
                // JobDetail jobDetail = sched.getJobDetail(JobKey.jobKey(jobName, jobGroupName));
                // Class<? extends Job> jobClass = jobDetail.getJobClass();
                // removeJob(jobName, jobGroupName, triggerName, triggerGroupName);
                // addJob(jobName, jobGroupName, triggerName, triggerGroupName, jobClass, cron);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  移除任务
     * @param jobName 任务名称
     * @param jobGroupName 任务组
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组
     */
    public static void removeJob(String jobName, String jobGroupName, String triggerName,
                                 String triggerGroupName) {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey);// 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有
     */
    public static List<SysQuartz> getAllJobs(){
        List<SysQuartz> list=new ArrayList<>();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            for (String groupName : scheduler.getJobGroupNames()) {
                for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                    SysQuartz sysQuartz=new SysQuartz();
                    sysQuartz.setJobName(jobKey.getName());
                    sysQuartz.setJobGroupName(jobKey.getGroup());
                    sysQuartz.setClassName(scheduler.getJobDetail(jobKey).getJobClass().getName());

                    List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    if(triggers.size()>0){
                        sysQuartz.setTriggerName(triggers.get(0).getKey().getName());
                        sysQuartz.setTriggerGroupName(triggers.get(0).getKey().getGroup());
                        if(triggers.get(0) instanceof  CronTrigger){
                            CronTrigger cronTrigger= (CronTrigger) triggers.get(0);
                            sysQuartz.setCornString(cronTrigger.getCronExpression());
                        };
                    }
                    list.add(sysQuartz);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     *  启动所有
     */
    public static void startJobs() {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            scheduler.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有
     */
    public static void shutdownJobs() {
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.setJobFactory(jobFactory);
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean pauseJob(String name,String group) throws SchedulerException {
               Scheduler scheduler=schedulerFactory.getScheduler();
               scheduler.pauseJob(new JobKey(name,group));
               return true;
    }

    public static boolean resumeJob(String name, String group) throws SchedulerException {
        Scheduler scheduler=schedulerFactory.getScheduler();
        scheduler.resumeJob(new JobKey(name,group));
        return true;
    }

    public static boolean clear() throws Exception{
        Scheduler scheduler=schedulerFactory.getScheduler();
        scheduler.clear();
        return true;
    }

    public static boolean deleteJob(String name, String group)throws Exception {
        Scheduler scheduler=schedulerFactory.getScheduler();
        scheduler.deleteJob(new JobKey(name,group));
        return true;
    }
}
