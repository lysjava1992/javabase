package com.learn.springboot.chapter2.controller;

import com.learn.springboot.chapter2.entity.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName PersonController
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/7/25 15:15
 * @Version 1.0
 **/
@RestController
public class PersonController {
    @GetMapping("/person/{id}")
    public Person person(@PathVariable("id") Long id,
                         @RequestParam(required = false,defaultValue = "King",name = "name") String name){
        Person person=new Person();
        person.setId(id);
        person.setName(name);
        return person;
    }
    @PostMapping(value = "/person",
    //请求类型
   // consumes 对应 请求头 “Content-Type”
    consumes = "application/properties+person",
    //返回类型
    //  produces   对应 请求头 “Accept”
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Person person(@RequestBody Person person){

        return person;
    }
}








