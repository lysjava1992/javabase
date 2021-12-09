package learn.alibaba.discovery.provide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DiscoveryController {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("list")
    public List<String> dis(){
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("nacos-discovery-provide");
        String url = serviceInstance.getUri() + "/list";
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(url);
        List<String> result = restTemplate.getForObject(url,List.class);
        return result;
    }


    @Autowired
    RestTemplate restTemplate;
    @GetMapping("list2")
    public List<String> list2(){
        List<String> result =restTemplate.getForObject("http://nacos-discovery-provide/list", List.class);
        return result;
    }
}
