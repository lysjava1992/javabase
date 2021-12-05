package learn.spring.cloud.consul.controller;

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
        Map<String,Object> map=new HashMap<>();
        map.put("services",discoveryClient.getServices());
        map.put("description", discoveryClient.description());
        return map;
    }

}
