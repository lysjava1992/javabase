package com.learn.brixton.feign.customer.service;

import com.learn.brixton.feign.customer.client.ComputeClient;
import org.springframework.stereotype.Service;

/**
 * 生产者服务故障时，本地处理
 */
@Service
public class ComputeClientHystrix implements ComputeClient{
    @Override
    public Integer add(Integer a, Integer b) {
        return -99999;
    }
}
