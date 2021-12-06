package learn.netflix.eureka.consumer.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class ServiceController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/info")
    public Map info(){
        // 通过服务名获取一个可用的服务地址
        // 虽然实现了请求的负载但需要手动拼接请求的url
        ServiceInstance serviceInstance=loadBalancerClient.choose("eureka-client");
        String url="http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/info";
        System.out.println(url);
        // 发起服务调用
       return restTemplate.getForObject(url,Map.class);
    }

}
