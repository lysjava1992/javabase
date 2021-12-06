package learn.netflix.eureka.consumer.hystrix.controller;

import learn.netflix.eureka.consumer.hystrix.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    InfoService  infoService;

    @GetMapping("/info")
    public Map info(){
        // 发起服务调用
        return infoService.info();
    }
    @GetMapping("/info2")
    public Map info2(){
        // 发起服务调用
        return infoService.info2();
    }
}
