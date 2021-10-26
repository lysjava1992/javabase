package com.simple.server.queue.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class Producer {
    private ConnectionFactory connectionFactory;
    private Channel channel;
    private Connection connection;
    private void init() throws IOException, TimeoutException {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.1.6");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setUsername("tb_rabbit");
        connectionFactory.setPassword("tb_rabbit");
        // 自动恢复连接
        connectionFactory.setAutomaticRecoveryEnabled(true);
//        connectionFactory.setConnectionTimeout(60000);
//        connectionFactory.setHandshakeTimeout(10000);
        this.connection=connectionFactory.newConnection();
        this.channel=connection.createChannel();
        channel.queueDeclare("test.0", false, false, false,new HashMap<>());
    }
    private void send(String msg) throws IOException {
        channel.basicPublish("", "test.0", null, msg.getBytes(StandardCharsets.UTF_8));
    }
    private void stop() throws IOException, TimeoutException {
        if(channel!=null){
            channel.close();
        }
        if(connection!=null){
            connection.close();
        }
    }
    public static void main(String[] args) throws Exception {
        Producer producer=new Producer();
        producer.init();
        producer.send("Hello");
        //producer.stop();
    }


}
