package com.learn.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class CustomListener implements TriggerListener {
    /**
     * 获取触发器名称
     * @return
     */
    @Override
    public String getName() {
        return "CustomListener";
    }

    /**
     *  触发器被触发
     *   execute（）方法执行之前
     * @param trigger
     * @param context
     */
    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        System.out.println(" Trigger 被触发了，此时Job 上的 execute() 方法将要被执行");
    }

    /**
     * 在 Trigger 触发后，Job 将要被执行时由 Scheduler 调用这个方法。
     *   TriggerListener 给了一个选择去否决 Job 的执行。
     *    假如这个方法返回 true，这个 Job 将不会为此次 Trigger 触发而得到执行。
     * @param trigger
     * @param context
     * @return
     */
    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("发现此次Job的相关资源准备存在问题，不便展开任务，返回true表示否决此次任务执行");
        return false;
    }

    /**
     * Scheduler 调用这个方法是在 Trigger 错过触发时。
     *   如这个方法的 JavaDoc 所指出的，
     *   你应该关注此方法中持续时间长的逻辑：
     *    在出现许多错过触发的 Trigger 时，长逻辑会导致骨牌效应。你应当保持这上方法尽量的小。
     * @param trigger
     */
    @Override
    public void triggerMisfired(Trigger trigger) {
        System.out.println( "当前Trigger触发错过了");
    }

    /**
     * Trigger 被触发并且完成了 Job 的执行时，
     * Scheduler 调用这个方法。
     *  这不是说这个 Trigger 将不再触发了，
     *  而仅仅是当前 Trigger 的触发(并且紧接着的 Job 执行) 结束时。
     *  这个 Trigger 也许还要在将来触发多次的。
     * @param trigger
     * @param context
     * @param triggerInstructionCode
     */
    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
        System.out.println("Trigger 被触发并且完成了 Job 的执行,此方法被调用");
    }
}
