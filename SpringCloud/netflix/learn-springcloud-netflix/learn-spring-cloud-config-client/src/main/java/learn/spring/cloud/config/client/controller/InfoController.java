package learn.spring.cloud.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${info.profile}")
    String profile;

    @GetMapping("/info")
    public String info(){
        return profile;
    }
}
