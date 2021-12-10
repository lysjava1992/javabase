package learn.spring.cloud.stream.queue.normal;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface SinkSendNormal {
    String OUTPUT = "input";
    /**
     * 发布
     * @return
     */
    @Output(SinkSendNormal.OUTPUT)
     MessageChannel output();
}
