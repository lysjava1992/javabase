package learn.alibaba.discovery.provide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class DiscoveryController {



    @Autowired
    WebClient.Builder webClientBuilder;
    @GetMapping("list")
    public  Mono<List> list(){
        Mono<List> result=webClientBuilder.build()
                .get()
                .uri("http://nacos-discovery-provide/list")
                .retrieve()
                .bodyToMono(List.class);
        return result;
    }
}
