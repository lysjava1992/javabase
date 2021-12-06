package learn.netflix.eureka.consumer.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ServiceController {

    /**
     * Ribbon 提供了基于服务名称进行REST服务间调用
     */
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/info")
    public Map info(){
        // 发起服务调用
        return restTemplate.getForObject("http://Eureka-Client/info",Map.class);
    }

}
