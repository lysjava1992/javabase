package com.learn.brixton.feign.customer.client;

import com.learn.brixton.feign.customer.service.ComputeClientHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * value  生产者服务名
 * fallback 本地熔断处理类
 */
@FeignClient(value = "compute-service",fallback = ComputeClientHystrix.class)
public interface ComputeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/add")
    Integer add(@RequestParam(value = "a") Integer a,@RequestParam(value = "b") Integer b);
}
