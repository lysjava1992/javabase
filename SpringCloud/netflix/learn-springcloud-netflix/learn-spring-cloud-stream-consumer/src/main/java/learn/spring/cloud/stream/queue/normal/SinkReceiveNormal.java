package learn.spring.cloud.stream.queue.normal;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;


public interface SinkReceiveNormal {
    String INPUT = "input";
    /**
     * 订阅
     * @return
     */
    @Input(SinkReceiveNormal.INPUT)
    SubscribableChannel input();


}
