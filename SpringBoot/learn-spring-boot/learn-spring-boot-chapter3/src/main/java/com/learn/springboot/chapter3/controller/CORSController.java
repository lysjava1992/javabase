package com.learn.springboot.chapter3.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 13:35
 **/
@RestController
@RequestMapping("/cors")
public class CORSController {

    @CrossOrigin(value = "http://localhost:8080")
    @GetMapping()
    public String cotsGet(){
        return "CORS GET请求";
    }


    @PostMapping()
    public String cotsPost(){
        return "CORS POST请求";
    }
}
