package learn.alibaba.dubbo.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/list")
    public List<String> dis(){

        return  discoveryClient.getServices();
    }
}
