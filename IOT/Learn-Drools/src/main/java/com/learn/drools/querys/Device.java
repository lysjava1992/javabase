package com.learn.drools.querys;

import lombok.Data;

@Data
public class Device {
    private float value;
    private String name;
    private boolean ok;
    private boolean isTransform;
    public Device(float value, String name) {
        this.value = value;
        this.name = name;
        this.ok=true;
    }

}
