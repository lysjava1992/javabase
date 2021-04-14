package com.learn.mqtt.netty.broker.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


@Data
public class ClientSession {
    private String clientId;
    private Channel channel;
    private boolean cleanSession;
    private boolean connection;
    private Set<MqttTopicSubscription> subscriptionSet=new HashSet<>();
    private final AtomicInteger msgIdSeq = new AtomicInteger(0);
    public ClientSession(String clientId, Channel channel, boolean cleanSession) {
        this.clientId = clientId;
        this.channel = channel;
        this.cleanSession = cleanSession;
        this.connection=true;

    }

    public void addSubscriptionSet(MqttTopicSubscription subscription) {
        this.subscriptionSet.add(subscription);
    }
    public MqttTopicSubscription getTopic(String topic){
       return subscriptionSet.stream().filter(mqttTopic-> topic.equals(mqttTopic.topicName())).findFirst().get();
    }
}
