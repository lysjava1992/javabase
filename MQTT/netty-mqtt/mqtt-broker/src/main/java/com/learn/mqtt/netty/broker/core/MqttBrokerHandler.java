package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.dc.pr.PRError;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static io.netty.handler.codec.mqtt.MqttMessageType.SUBACK;
import static io.netty.handler.codec.mqtt.MqttMessageType.UNSUBACK;
import static io.netty.handler.codec.mqtt.MqttQoS.AT_MOST_ONCE;


@Slf4j
public class MqttBrokerHandler extends ChannelInboundHandlerAdapter {
    private MqttBrokerContext context;
    private ClientSession session;
    private String clientId;

    public String getClientId() {
        return this.clientId;
    }

    public MqttBrokerHandler(MqttBrokerContext context) {
        this.context = context;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MqttMessage) {
            MqttMessage mqttMsg = (MqttMessage) msg;
            if (mqttMsg.decoderResult().isSuccess()) {
                processMqttMsg(ctx, mqttMsg);
            }
        } else {
            ctx.close();
        }
    }

    private void processMqttMsg(ChannelHandlerContext ctx, MqttMessage mqttMsg) {
        switch (mqttMsg.fixedHeader().messageType()) {
            case CONNECT:
                processConnect(ctx, (MqttConnectMessage) mqttMsg);
                break;
            case SUBSCRIBE:
                processSubscribe(ctx, (MqttSubscribeMessage) mqttMsg);
                break;
            case UNSUBSCRIBE:
                processUnSubscribe(ctx, (MqttUnsubscribeMessage) mqttMsg);
                break;
            case PUBLISH:
                processPublish(ctx,mqttMsg);
                break;
            case PINGREQ:
                // 心跳请求
                ctx.writeAndFlush(new MqttMessage(new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_MOST_ONCE, false, 0)));
                break;
            case DISCONNECT:
                processDisconnect(ctx, mqttMsg);
                break;
            default:
                processOther(ctx, mqttMsg);
                break;
        }
    }




    private boolean checkoutTopic(String topic) {
        if (topic.contains("#") || topic.contains("+")) {
            return false;
        }
        return true;
    }

    private boolean checkConnected(ChannelHandlerContext ctx) {
        if (this.session != null && this.session.isCleanSession()) {
            return true;
        }
        ctx.close();
        return false;
    }
    private void processPublish(ChannelHandlerContext ctx, MqttMessage mqttMsg) {
        MqttQoS mqttQoS= mqttMsg.fixedHeader().qosLevel();
        switch (mqttQoS){
            case AT_MOST_ONCE:
                context.publish((MqttPublishMessage) mqttMsg);
                break;
            case AT_LEAST_ONCE:
            case EXACTLY_ONCE:
                log.info("");
        }

    }
    private void processUnSubscribe(ChannelHandlerContext ctx, MqttUnsubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        for (String topic : mqttMsg.payload().topics()) {
            context.unSubscription(topic, this.clientId);
            log.info("客户端【{}】 取消订阅主题【{}】", clientId, topic);
        }
        ctx.writeAndFlush(createMqttUnsubAckMessage(mqttMsg.variableHeader().messageId()));
    }
    /**
     * 客户端订阅主题
     *
     * @param ctx
     * @param mqttMsg
     */
    private void processSubscribe(ChannelHandlerContext ctx, MqttSubscribeMessage mqttMsg) {
        if (!checkConnected(ctx)) {
            return;
        }
        List<MqttTopicSubscription> mqttTopicSubscriptions = mqttMsg.payload().topicSubscriptions();
        List<Integer> mqttQoSList = new ArrayList<>();
        for (MqttTopicSubscription subscription : mqttTopicSubscriptions) {
            String topic = subscription.topicName();
            if (!checkoutTopic(topic)) {
                mqttQoSList.add(128);
                continue;
            }
            MqttQoS reqQoS = subscription.qualityOfService();
            this.session.addSubscriptionSet(subscription);
            mqttQoSList.add(reqQoS.value());
            context.addSubscription(topic, this.clientId);
        }
        ctx.writeAndFlush(createSubAckMessage(mqttMsg.variableHeader().messageId(), mqttQoSList));

    }
    /**
     * 断开连接 不必回复客户端
     *
     * @param ctx
     * @param mqttMsg
     */
    private void processDisconnect(ChannelHandlerContext ctx, MqttMessage mqttMsg) {
        ctx.close();
        context.removeSession(this.clientId);
    }

    private void processOther(ChannelHandlerContext ctx, MqttMessage mqttMsg) {
        log.info("MqttMessage [{}]", mqttMsg);
        log.info("MessageType [{}]", mqttMsg.fixedHeader().messageType());
    }

    /**
     * 当前仅支持 用户名/密码
     *
     * @param ctx
     * @param mqttMsg
     */
    private void processConnect(ChannelHandlerContext ctx, MqttConnectMessage mqttMsg) {
        String clientId = mqttMsg.payload().clientIdentifier();
        this.clientId = clientId;
        String username = mqttMsg.payload().userName();
        byte[] bytes = mqttMsg.payload().passwordInBytes();
        String password = new String(bytes);
        if (StringUtils.isEmpty(clientId)) {
            ctx.writeAndFlush(createMqttConnAckMessage(MqttConnectReturnCode.CONNECTION_REFUSED_SERVER_UNAVAILABLE, mqttMsg));
        }
        log.info("客户端ID【{}】;用户名【{}】;密码（无加密）【{}】", clientId, username, password);
        //  TODO 认证

        // 认证通过注册
        registerClient(mqttMsg, ctx);

    }

    /**
     * 连接注册
     * 创建session(是否存在)
     *
     * @param mqttMsg
     * @param ctx
     */
    private void registerClient(MqttConnectMessage mqttMsg, ChannelHandlerContext ctx) {
        String clientId = mqttMsg.payload().clientIdentifier();
        ClientSession clientSession = context.getClientSession(clientId);
        if (clientSession != null && clientSession.isConnection()) {
            // 客户端存在，已经连接拒绝
            ctx.writeAndFlush(createMqttConnAckMessage(MqttConnectReturnCode.CONNECTION_REFUSED_SERVER_UNAVAILABLE, mqttMsg));
            log.info("客户端[{}]已存在", this.clientId);
            ctx.close();
            return;
        }
        context.registerClient(mqttMsg, ctx);
        //组包 返回确认消息
        MqttConnAckMessage ackMessage = createMqttConnAckMessage(MqttConnectReturnCode.CONNECTION_ACCEPTED, mqttMsg);
        ctx.writeAndFlush(ackMessage);
        this.session = context.getClientSession(clientId);
    }

    /**
     * 组包
     * 连接消息确认
     * 消息头
     * MqttFixedHeader(
     * MqttMessageType messageType,  类型
     * boolean isDup,             是否是重传
     * MqttQoS qosLevel,         消息质量
     * boolean isRetain,         是否持久化（发布）
     * int remainingLength       剩余长度
     * )
     * 可变表头
     * MqttConnAckVariableHeader(MqttConnectReturnCode connectReturnCode, boolean sessionPresent,
     * MqttProperties properties) {
     * this.connectReturnCode = connectReturnCode; 返回码
     * this.sessionPresent = sessionPresent;     用于服务端是否在客户端断开之后存储订阅主题及消息
     * // 连接确认 无负载
     * this.properties = MqttProperties.withEmptyDefaults(properties);
     * }
     * MqttConnAckMessage(header,variableHeader)
     */
    private MqttConnAckMessage createMqttConnAckMessage(MqttConnectReturnCode connectionAccepted, MqttConnectMessage mqttMsg) {
        MqttFixedHeader header = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttConnAckVariableHeader variableHeader = new MqttConnAckVariableHeader(connectionAccepted, !mqttMsg.variableHeader().isCleanSession());
        MqttConnAckMessage mqttConnAckMessage = new MqttConnAckMessage(header, variableHeader);
        return mqttConnAckMessage;
    }

    private MqttSubAckMessage createSubAckMessage(Integer msgId, List<Integer> grantedQoSList) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(SUBACK, false, AT_MOST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(msgId);
        MqttSubAckPayload mqttSubAckPayload = new MqttSubAckPayload(grantedQoSList);

        return new MqttSubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader, mqttSubAckPayload);
    }

    private MqttUnsubAckMessage createMqttUnsubAckMessage(int messageId) {
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(UNSUBACK, false, AT_MOST_ONCE, false, 0);
        MqttMessageIdVariableHeader mqttMessageIdVariableHeader = MqttMessageIdVariableHeader.from(messageId);
        return new MqttUnsubAckMessage(mqttFixedHeader, mqttMessageIdVariableHeader);
    }

}
