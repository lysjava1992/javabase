package com.learn.mqtt.netty.broker.core;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class MqttBrokerInitializer extends ChannelInitializer<SocketChannel> {
  private MqttBrokerContext context;

    public MqttBrokerInitializer(MqttBrokerContext context) {
        this.context = context;
    }

    //负责客户端接入
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
          ChannelPipeline pipeline=socketChannel.pipeline();
          pipeline.addFirst("idle", new IdleStateHandler(0, 0, 60));
          pipeline.addLast("decoder",new MqttDecoder());
          pipeline.addLast("encoder", MqttEncoder.INSTANCE);
          pipeline.addLast(new MqttBrokerHandler(this.context));
    }
}
