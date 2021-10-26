package com.learn.mqtt.netty.broker.core;

import com.learn.mqtt.netty.broker.message.RetainMessage;
import com.learn.mqtt.netty.broker.session.ClientSession;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.*;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 心跳监测超时
     * 客户端掉线
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        context.removeSession(clientId);
        // TODO  遗嘱消息
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        context.removeSession(clientId);
    }


    private void processMqttMsg(ChannelHandlerContext ctx, MqttMessage mqttMsg) {
        switch (mqttMsg.fixedHeader().messageType()) {
            case CONNECT:
                // 连接    client -> broker
                processConnect(ctx, (MqttConnectMessage) mqttMsg);
                break;
            case SUBSCRIBE:
                // 订阅主题 client -> broker
                processSubscribe(ctx, (MqttSubscribeMessage) mqttMsg);
                break;
            case UNSUBSCRIBE:
                // 取消订阅 client -> broker
                processUnSubscribe(ctx, (MqttUnsubscribeMessage) mqttMsg);
                break;
            case PUBLISH:
                // 发布消息 client -> broker
                processPublish(ctx, mqttMsg);
                break;
            case PUBACK:
                // 客户端以Q1 订阅消息，消息收到时的确认 不用回
                break;
            case PUBREL:
                // 客户端以Q2发布到Broker
                // Q2消息客户端确认 client->broker
                processPubRel(ctx, (MqttMessageIdVariableHeader) mqttMsg.variableHeader());
                break;
            case PUBREC:
                // 客户端以Q2订阅的消息
                // 第一阶段客户端收到消息确认 client->broker
                processPubRec(ctx, (MqttMessageIdVariableHeader) mqttMsg.variableHeader());
                break;
            case PUBCOMP:
                // 客户端以Q2订阅的消息
                // 客户端处理完消息返回确认 client->broker
                // 服务端可以清除该消息的持久化信息，不用返回
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
        MqttQoS mqttQoS = mqttMsg.fixedHeader().qosLevel();
        MqttPublishMessage publishMessage = (MqttPublishMessage) mqttMsg;
        String topic = publishMessage.variableHeader().topicName();
        switch (mqttQoS) {
            case AT_MOST_ONCE:
                //最多一次
                context.publish(publishMessage);
                break;
            case AT_LEAST_ONCE:
                //最少一次
                 // 1.存储
                 // 2.发布
                context.publish(publishMessage);
                // 3.删除
                // 4.返回确认
                ctx.writeAndFlush(createPubAckMessage(publishMessage.variableHeader().packetId()));
                break;
            case EXACTLY_ONCE:
                //只有一次
                // 存储消息编号
                // 发布消息
                context.publish(publishMessage);
                // 返回第一步确认
                ctx.writeAndFlush(createPubRecMessage(publishMessage.variableHeader().packetId()));
                break;
        }
        // 是否持久化
        if (publishMessage.fixedHeader().isRetain()) {
            byte[] messageBytes = new byte[publishMessage.payload().readableBytes()];
            publishMessage.payload().getBytes(publishMessage.payload().readerIndex(), messageBytes);
            if (messageBytes.length == 0) {
                context.removeRetainMessage(topic);
            } else {
                context.updateRetainMessage(topic, new RetainMessage(topic, mqttQoS, messageBytes));
            }
        }
    }

    private void processPubRel(ChannelHandlerContext ctx, MqttMessageIdVariableHeader header) {
        ctx.writeAndFlush(createPubCompMessage(header.messageId()));
    }

    private void processPubRec(ChannelHandlerContext ctx, MqttMessageIdVariableHeader variableHeader) {
        ctx.writeAndFlush(createPubRelMessage(variableHeader.messageId()));
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
        mqttTopicSubscriptions.forEach(topic -> {
            RetainMessage retainMessage = context.getRetainMessage(topic.topicName());
            if (retainMessage != null) {
                ctx.writeAndFlush(createMqttPublishMsg(retainMessage.getTopic(),retainMessage.getQoS(),retainMessage.getMessage()));
            }
        });
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


        // 覆盖默认心跳
        //设置客户端心跳
        //如果Keep Alive的值非0，而且服务端在一个半Keep Alive的周期内没有收到客户端的控制包，服务端必须作为网络故障断开网络连接
        if (mqttMsg.variableHeader().keepAliveTimeSeconds() > 0) {
            if (ctx.channel().pipeline().names().contains("idle")) {
                ctx.channel().pipeline().remove("idle");
            }
            ctx.channel().pipeline().addFirst("idle", new IdleStateHandler(0, 0, Math.round(mqttMsg.variableHeader().keepAliveTimeSeconds() * 1.5f)));
        }
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

    /**
     * 发布 Q1 消息 broker确认
     *
     * @param messageId
     * @return
     */
    private MqttPubAckMessage createPubAckMessage(int messageId) {
        MqttPubAckMessage pubAckMessage = (MqttPubAckMessage) MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId), null);
        return pubAckMessage;
    }

    /**
     * 客户端发布消息Q2 第一步确认
     * broker->client
     *
     * @param messageId
     */
    private MqttMessage createPubRecMessage(int messageId) {
        MqttMessage pubRecMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBREC, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId), null);
        return pubRecMessage;
    }

    /**
     * 客户端发布消息Q2 第二步确认
     * broker->client
     *
     * @param messageId
     */
    private MqttMessage createPubCompMessage(int messageId) {
        MqttMessage pubRelMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBCOMP, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId), null);
        return pubRelMessage;
    }

    /**
     * 客户端订阅 Q2
     * broker确认消息
     *
     * @param messageId
     */
    private MqttMessage createPubRelMessage(int messageId) {
        MqttMessage pubRecMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBREC, false, MqttQoS.AT_MOST_ONCE, false, 0),
                MqttMessageIdVariableHeader.from(messageId), null);
        return pubRecMessage;
    }
    protected MqttPublishMessage createMqttPublishMsg( String topic, MqttQoS qos, byte[] messageBytes) {
        //订阅者消息质量
        MqttTopicSubscription mqttTopic=session.getTopic(topic);
        MqttFixedHeader mqttFixedHeader =
                new MqttFixedHeader(MqttMessageType.PUBLISH, false, mqttTopic.qualityOfService().value()<=qos.value()? mqttTopic.qualityOfService() : qos, false, 0);
        MqttPublishVariableHeader header = new MqttPublishVariableHeader(topic, mqttFixedHeader.qosLevel().value()>0?session.getMsgIdSeq().get():0);
        return new MqttPublishMessage(mqttFixedHeader, header, Unpooled.buffer().writeBytes(messageBytes));
    }
}
