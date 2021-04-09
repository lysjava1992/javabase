package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.buffer.ByteBuf;
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
     * List<String>订阅主题的客户端ID
     */
    private ConcurrentHashMap<String, Set<String>>  topicMap=new ConcurrentHashMap();
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
           for (String clientName : clientNames) {
               ByteBuf buf=mqttMsg.content();
               ClientSession session= sessionMap.get(clientName);
               MqttPublishMessage message=createMqttPublishMsg(session,topic,buf);
               buf.retain();
              session.getChannel().writeAndFlush(message);
           }
       }

    }
    protected MqttPublishMessage createMqttPublishMsg(ClientSession session, String topic, ByteBuf payload) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(MqttMessageType.PUBLISH, false, AT_MOST_ONCE , false, 0);
        MqttPublishVariableHeader header = new MqttPublishVariableHeader(topic, session.getMsgIdSeq().get());
        return new MqttPublishMessage(mqttFixedHeader, header, payload);
    }
}
