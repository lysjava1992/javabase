package learn.alibaba.discovery.provide.controller;

import learn.alibaba.discovery.provide.service.DiscoveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 配置文件动态刷新
 */
@RefreshScope
@RestController
public class DiscoveryController {

    @Autowired
    DiscoveryService discoveryService;
    @GetMapping("list")
    public List<String> list(){
        List<String> result =discoveryService.services();
        return result;
    }

    @Value("${test}")
    private String value;

    @GetMapping("hello")
    public String hello(){

        return value;
    }
}
