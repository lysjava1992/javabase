package com.imooc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName TestController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/13 15:54
 * @Version 1.0
 **/
@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "Hello!";
    }
}
