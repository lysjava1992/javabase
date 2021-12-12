package learn.alibaba.discovery.sentinel.controller;

import learn.alibaba.discovery.sentinel.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 对于controller层的限流
 *  sentinel天然支持
 *  不需要在代码中额外配置
 */
@RestController
public class DiscoveryController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/list")
    public List<String> dis(){
        return  discoveryClient.getServices();
    }

    @Autowired
    HelloService helloService;



    @GetMapping("hello")
    public String hello(){
        return helloService.hello();
    }


    @GetMapping("hello2")
    public String hello2(){
        return helloService.hello2();
    }
}
