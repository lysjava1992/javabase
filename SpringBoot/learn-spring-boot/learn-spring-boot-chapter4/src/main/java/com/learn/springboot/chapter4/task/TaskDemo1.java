package com.learn.springboot.chapter4.task;
import com.learn.springboot.chapter4.componet.CustomScheduling;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

//@Component
public class TaskDemo1 extends CustomScheduling {
    //控制是否执行
  //  @Value(value = "${task.taskName1.switch}")
    private Boolean isSwitch=true;

  //  @Value(value = "${task.taskName1.cron}")
    private String cron="0/5 * * * * *";

    @Override
    protected void processTask() {
        if (isSwitch){
            System.out.println("基于接口SchedulingConfigurer的动态定时任务:"
                    + LocalDateTime.now()+"，线程名称："+Thread.currentThread().getName()
                    + " 线程id："+Thread.currentThread().getId());
        }
    }

    @Override
    protected String getCron() {
        return cron;
    }
}
