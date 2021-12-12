package learn.alibaba.dubbo.consumer.service;
import learn.alibaba.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "learn.alibaba.dubbo.provide.service.HelloServiceImpl";
    }
}
