package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.message.RetainMessage;
import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt. *;
import lombok.extern.slf4j.Slf4j;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;

@Slf4j
public class DefaultMqttBrokerContext implements  MqttBrokerContext {
    /**
     * String         客户端 id
     * ClientSession  客户端 session
     */
    private ConcurrentHashMap<String,ClientSession>  sessionMap=new ConcurrentHashMap();

    /**
     *  当前不支持匹配
     * String 主题
     * Set<String>订阅主题的客户端ID
     */
    private ConcurrentHashMap<String, Set<String>>  topicMap=new ConcurrentHashMap();

    /**
     * <String(主题),MqttMessage（最后一条retain消息）>
     */
    private ConcurrentHashMap<String, RetainMessage >   retainMessageCache=new ConcurrentHashMap<>();

    public ClientSession getClientSession(String clientId) {
        return sessionMap.get(clientId);
    }


    public void registerClient(MqttConnectMessage mqttMsg, ChannelHandlerContext ctx) {
        String clientId=mqttMsg.payload().clientIdentifier();
        ClientSession clientSession=sessionMap.get(clientId);
        if(clientSession==null){
            clientSession=new ClientSession(clientId,ctx.channel(),mqttMsg.variableHeader().isCleanSession());
        }else {
            clientSession.setConnection(true);
        }
        sessionMap.put(clientId,clientSession);
    }

    @Override
    public void removeSession(String clientId) {
        ClientSession session=sessionMap.get(clientId);
        if(session.isCleanSession()){
            sessionMap.remove(clientId);
        }else {
            session.setConnection(false);
        }
        log.info("客户端[{}] 断开连接,是否清除session [{}]",clientId,session.isCleanSession());
    }

    @Override
    public void addSubscription(String topic, String clientId) {
         Set<String> clients=topicMap.getOrDefault(topic, new HashSet<>());
         clients.add(clientId);
         topicMap.put(topic,clients);
    }

    @Override
    public void unSubscription(String topic, String clientId) {
        Set<String> clients=topicMap.get(topic);
        clients.remove(clientId);
        topicMap.put(topic,clients);
    }

    /**
     * 发布消息
     * @param mqttMsg
     */
    @Override
    public void publish(MqttPublishMessage mqttMsg) {
       String topic= mqttMsg.variableHeader().topicName();
       Set<String> clientNames=topicMap.get(topic);
       if(clientNames!=null){
           byte[] messageBytes = new byte[mqttMsg.payload().readableBytes()];
           mqttMsg.payload().getBytes(mqttMsg.payload().readerIndex(), messageBytes);
           for (String clientName : clientNames) {
               ClientSession session= sessionMap.get(clientName);
               MqttPublishMessage message=createMqttPublishMsg(session,topic,mqttMsg.fixedHeader().qosLevel(),messageBytes);
               session.getChannel().writeAndFlush(message);
           }
       }
    }

    @Override
    public void updateRetainMessage(String topic,  RetainMessage retainMessage) {
       retainMessageCache.put(topic,retainMessage);
    }

    @Override
    public void removeRetainMessage(String topic) {
        retainMessageCache.remove(topic);
    }

    @Override
    public RetainMessage getRetainMessage(String topic) {
        return retainMessageCache.get(topic);
    }

    /**
     *
     * 消息由 broker->client
     *  订阅者
     * @return
     */
    protected MqttPublishMessage createMqttPublishMsg(ClientSession session, String topic, MqttQoS qos, byte[] messageBytes) {
        //订阅者消息质量
        MqttTopicSubscription mqttTopic=session.getTopic(topic);
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(MqttMessageType.PUBLISH, false, mqttTopic.qualityOfService().value()<=qos.value()? mqttTopic.qualityOfService() : qos, false, 0);
        MqttPublishVariableHeader header = new MqttPublishVariableHeader(topic, mqttFixedHeader.qosLevel().value()>0?session.getMsgIdSeq().get():0);
        return new MqttPublishMessage(mqttFixedHeader, header, Unpooled.buffer().writeBytes(messageBytes));
    }
}
