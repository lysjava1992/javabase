package com.learn.netflix.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ServiceController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("info")
    public Map info(){
        try {
            // 触发熔断
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("services",discoveryClient.getServices());
        map.put("description", discoveryClient.description());
        return map;
    }
    @GetMapping("info2")
    public Map info2(){
        Map<String,Object> map=new HashMap<>();
        map.put("services",discoveryClient.getServices());
        map.put("description", discoveryClient.description());
        return map;
    }

    @PostMapping(value = "file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String saveFile(@RequestPart(value = "file") MultipartFile file){
        System.out.println(file.getSize());
        return file.getName();
    }
}
