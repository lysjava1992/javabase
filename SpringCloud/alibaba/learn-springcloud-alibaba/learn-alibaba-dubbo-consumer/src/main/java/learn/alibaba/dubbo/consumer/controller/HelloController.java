package learn.alibaba.dubbo.consumer.controller;
import learn.alibaba.dubbo.api.HelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Reference
    HelloService helloService;


    @GetMapping("/hello")
    public String hello(){
        return  helloService.hello();
    }
}
