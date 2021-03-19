package com.learn.job;
import lombok.SneakyThrows;
import org.quartz.*;

import java.time.LocalDateTime;
/**
 1.  若不使用以下两个注解，则count输出永远是1
 2.  若只使用@PersistJobDataAfterExecution在两个触发器相同时则会输出(1,1 ;2,2;3,3;...)
 3.  使用了两个注解则count会递增输出(1,2,3,4,5,....)
 **/
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HelloJob2 implements Job {
    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        int count= (int) context.getJobDetail().getJobDataMap().get("count");
        count++;
        context.getJobDetail().getJobDataMap().put("count",count);
        System.out.println("执行【"+LocalDateTime.now()+"】【"+count+"】【"+context.getTrigger().getJobDataMap().get("name")+"】");
      //  Thread.sleep(5000);
    }
}
