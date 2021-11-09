package com.learn.drools.iot;

import lombok.Data;

@Data
public class Alarm {
    private long time;
    private Sensor  sensor;
    private String type;
    public Alarm() {
        time=System.currentTimeMillis();
    }
    public Alarm(String type,Sensor sensor) {
        time=System.currentTimeMillis();
        this.sensor=sensor;
        this.type=type;
    }

    public void warning(){
        System.err.println(sensor.getName()+"-普通预警-"+sensor.getStatus()+sensor.getValue());
    }
    public void alarm(){
        System.err.println(sensor.getName()+"-严重报警-"+sensor.getStatus()+sensor.getValue());
    }
}
