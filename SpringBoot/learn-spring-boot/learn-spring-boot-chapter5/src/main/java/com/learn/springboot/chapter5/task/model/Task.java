package com.learn.springboot.chapter5.task.model;
import lombok.Data;
import java.util.Date;

@Data
public class Task {
    private long id;
    private String triggerName;
    private String cron;
    private State state;
    private Date netExecute;

    public Task(long id, String triggerName, String cron, State state, Date netExecute) {
        this.id = id;
        this.triggerName = triggerName;
        this.cron = cron;
        this.state = state;
        this.netExecute = netExecute;
    }

    public enum State{
        RUN,WAITTING_NEXT,STOP
    }
}
