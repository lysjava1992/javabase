package com.learn.mqtt.netty.broker.core;
import com.learn.mqtt.netty.broker.MqttBrokerApplication;

import java.util.List;

public class MqttTest {
    public static void main(String[] args) throws Exception {
        MqttBrokerApplication broker = new MqttBrokerApplication.Build()
                .host("192.168.1.7")
                .port(1881)
                .build();
        broker.start();
    }

}
