package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

public interface MqttBrokerContext {
    ClientSession getClientSession(String clientId);

    void registerClient(MqttConnectMessage mqttMsg, ChannelHandlerContext ctx);

    void removeSession(String clientId);

    void addSubscription(String topic, String clientId);
    void unSubscription(String topic, String clientId);
    void publish(MqttPublishMessage mqttMsg);
}