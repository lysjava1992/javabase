package learn.alibaba.dubbo.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("nacos-discovery-provide")
public interface DiscoveryService {
    @GetMapping("/list")
    List<String> services();
}
