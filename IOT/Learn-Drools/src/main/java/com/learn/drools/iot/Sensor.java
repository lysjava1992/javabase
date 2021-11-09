package com.learn.drools.iot;

import lombok.Data;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.UUID;

/**
 *  传感器
 */
@Data
public class Sensor {
    /**
     *  名称
     */
    private String name;
    /**
     * id
     */
    private UUID deviceId;
    /**
     * 类型
     */
    private String type;
    /**
     * 值
     */
    private float value;
    /**
     * 状态
     */
    private String status;

    private boolean isDeal;


    public Sensor() {
        deviceId= UUID.randomUUID();
    }


}
