package com.learn.entity;

import lombok.Data;

@Data
public class SysQuartz {
    private String jobName;
    private String jobGroupName;
    private String triggerName;
    private String triggerGroupName;
    private String className;
    private String cornString;

    public SysQuartz() {
    }

    public SysQuartz(String jobName, String jobGroupName, String triggerName, String triggerGroupName, String className, String cornString) {
        this.jobName = jobName;
        this.jobGroupName = jobGroupName;
        this.triggerName = triggerName;
        this.triggerGroupName = triggerGroupName;
        this.className = className;
        this.cornString = cornString;
    }
}
