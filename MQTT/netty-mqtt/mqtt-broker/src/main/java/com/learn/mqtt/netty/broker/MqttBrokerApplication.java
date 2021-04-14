package com.learn.mqtt.netty.broker;
import com.learn.mqtt.netty.broker.core.DefaultMqttBrokerContext;
import com.learn.mqtt.netty.broker.core.MqttBrokerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MqttBrokerApplication {
     private String host;
     private int port;
     private int bossGroupCount;
     public int workGroupCount;
     private EventLoopGroup bossGroup;
     private EventLoopGroup workGroup;
     private boolean keepAline;
     private Channel serverChannel;
    private MqttBrokerApplication(Build build) {
        this.host=build.host;
        this.port=build.port;
        this.bossGroupCount=build.bossGroupCount;
        this.workGroupCount=build.workGroupCount;
        this.keepAline=build.keepAline;
    }

    public void start() throws InterruptedException {
        bossGroup=new NioEventLoopGroup(bossGroupCount);
        workGroup=new NioEventLoopGroup(workGroupCount);
        ServerBootstrap bootstrap=new ServerBootstrap();
        DefaultMqttBrokerContext context=new DefaultMqttBrokerContext();
        bootstrap.group(bossGroup,workGroup)
                 .channel(NioServerSocketChannel.class)
                 .childHandler(new MqttBrokerInitializer(context))
                 .childOption(ChannelOption.SO_KEEPALIVE,keepAline);
        serverChannel=bootstrap.bind(host,port).sync().channel();
       log.info("MQTT Broker is starting [{}]-[{}]",host,port);
    }
    public void shutdown(){
        try {
            serverChannel.close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    public static class Build{
        private String host="127.0.0.1";
        private int port=1883;
        private int bossGroupCount=1;
        private int workGroupCount=8;
        private boolean keepAline;
        public Build host(String host) {
            this.host = host;
            return this;
        }

        public Build port(int port) {
            this.port = port;
            return this;
        }

        public Build bossGroupCount(int bossGroupCount) {
            this.bossGroupCount = bossGroupCount;
            return this;
        }

        public Build workGroupCount(int workGroupCount) {
            this.workGroupCount = workGroupCount;
            return this;
        }
        public Build keepAline(boolean keepAline) {
            this.keepAline = keepAline;
            return this;
        }
        public MqttBrokerApplication build() {
            return new MqttBrokerApplication(this);
        }
    }
}
