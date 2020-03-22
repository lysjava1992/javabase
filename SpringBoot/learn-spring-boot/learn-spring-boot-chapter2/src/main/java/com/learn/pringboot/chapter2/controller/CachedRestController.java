package com.learn.pringboot.chapter2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CachedRestController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/22 14:01
 * @Version 1.0
 **/
@Controller
public class CachedRestController {

    /**
     * 可出现的状态码
     *  200
     *  500
     *  400
     *  不会出现304
     * @return
     */
    @RequestMapping("/cached1")
    @ResponseBody
    public String  helloWorld(){

         return "Hello World";
    }

    @RequestMapping("/cached")
    public ResponseEntity<String> cached(@RequestParam(required = false,defaultValue = "false")boolean cached){
        if(cached){
            return new ResponseEntity(HttpStatus.NOT_MODIFIED);
        }else {
            return  ResponseEntity.ok("HelloWorld");
        }


    }
}








