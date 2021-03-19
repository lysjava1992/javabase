package com.learn.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

import java.util.Map;

/**
 * 利用触发器在微服务模式下
 *  开发分布式锁
 *  待完善
 */
public class CustomTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "customTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {

    }

    /**
     *  是否执行
     *  在分布式 微服务下
     *  可以利用Redis来加锁避免重复执行
     *    通过lockKey判断是否有值
     *    给lockKey设置一个过期值
     *  适用于循环执行间隔较大的情况下
     * @param trigger
     * @param context
     * @return  true 跳过 / false 执行
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        Map<String,Object> paramsMap=context.getMergedJobDataMap();
        String lockKey=String.format("%s:%s:lock",trigger.getJobKey().getGroup(),trigger.getJobKey());
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {

    }
}
