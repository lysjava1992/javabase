package com.learn.brixton.ribbon.customer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "addFallBack")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String add(){
        //通过服务名称查找服务
        return restTemplate.getForEntity("http://COMPUTE-SERVICE/add?a=1&b=2",String.class).getBody();
    }

    public String addFallBack(){
        return "熔断触发";
    }

}
