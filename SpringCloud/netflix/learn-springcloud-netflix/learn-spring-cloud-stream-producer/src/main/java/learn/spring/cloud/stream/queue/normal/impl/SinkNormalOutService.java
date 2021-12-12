package learn.spring.cloud.stream.queue.normal.impl;

import learn.spring.cloud.stream.queue.normal.SinkSendNormal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(value = {SinkSendNormal.class})
public class SinkNormalOutService {

    @Autowired
    SinkSendNormal sinkSendNormal;

    @Bean
    public MessageChannel inputChannel() {
        return new DirectChannel();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        try {
            while (true) {
                send();
                Thread.sleep(5000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send() {
        Message message = MessageBuilder
                .withPayload("Hello").build();
        sinkSendNormal.output().send(message);
    }
}
