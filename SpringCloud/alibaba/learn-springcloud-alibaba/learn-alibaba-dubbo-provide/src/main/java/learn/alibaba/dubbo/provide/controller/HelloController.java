package learn.alibaba.dubbo.provide.controller;
import learn.alibaba.dubbo.api.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @Autowired
    HelloService helloService;


    @GetMapping("/hello")
    public String hello(){
        return  helloService.hello();
    }
}
