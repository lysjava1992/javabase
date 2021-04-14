package com.learn.mqtt.netty.broker.message;

import io.netty.handler.codec.mqtt.MqttQoS;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *  Retain 消息
 */
@Data
@AllArgsConstructor
public class RetainMessage implements Serializable {
    private String topic;
    private MqttQoS qoS;
    private byte[] message;
}
