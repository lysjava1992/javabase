package learn.spring.cloud.stream.queue.normal.impl;
import learn.spring.cloud.stream.queue.normal.SinkReceiveNormal;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;


@Service
@EnableBinding(value = {SinkReceiveNormal.class})
public class SinkNormalInputService {

    @StreamListener(SinkReceiveNormal.INPUT)
    public void receive(Object payload) {
        System.out.println("Received: " + payload);
    }
}
