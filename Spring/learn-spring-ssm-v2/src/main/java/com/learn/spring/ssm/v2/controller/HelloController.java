package com.learn.spring.ssm.v2.controller;

import com.learn.spring.ssm.v2.entity.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.util.calendar.LocalGregorianCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 09:04
 **/
@Controller
public class HelloController {

    @GetMapping({"","/"})
    public String index(){
        return "index";
    }

    @ResponseBody
    @GetMapping({"str"})
    public String string(){
        return "Hello World";
    }

    @ResponseBody
    @GetMapping({"pojo/{name}"})
    public Customer pojo(@PathVariable("name") String name){
        Customer customer=new Customer();
        customer.setCreateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));
        customer.setId(1001L);
        customer.setUsername(name);
        customer.setPassword(UUID.randomUUID().toString());
        return customer;
    }
}
