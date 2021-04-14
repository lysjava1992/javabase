package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.message.RetainMessage;
import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;

public interface MqttBrokerContext {
    /**
     * 通过客户端Id获取MQTT的Session
     * @param clientId
     * @return
     */
    ClientSession getClientSession(String clientId);

    /**
     * 注册
     * @param mqttMsg 连接消息包
     * @param ctx 通道 channel
     */
    void registerClient(MqttConnectMessage mqttMsg, ChannelHandlerContext ctx);

    /**
     *  删除session
     * @param clientId
     */
    void removeSession(String clientId);

    /**
     *   客户端订阅主题
     * @param topic 主题
     * @param clientId 客户端Id
     */
    void addSubscription(String topic, String clientId);

    /**
     *   客户端取消对topic的订阅
     * @param topic 主题
     * @param clientId 客户端Id
     */
    void unSubscription(String topic, String clientId);

    /**
     *  发布消息
     *  消息从broker发送到订阅的客户端
     * @param mqttMsg
     */
    void publish(MqttPublishMessage mqttMsg);

    /**
     * 更新Retain消息
     *  一个主题只有一条Retain消息
     * @param topic
     * @param
     */
    void updateRetainMessage(String topic, RetainMessage retainMessage);

    /**
     *  删除主题对应的Retain消息
     * @param topic
     */
    void removeRetainMessage(String topic);

    /**
     *  获取某条主题保留消息
     * @param topic
     * @return
     */
    RetainMessage getRetainMessage(String topic);
}