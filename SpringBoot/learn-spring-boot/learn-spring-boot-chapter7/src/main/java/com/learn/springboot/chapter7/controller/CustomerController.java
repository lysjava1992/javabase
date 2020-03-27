package com.learn.springboot.chapter7.controller;

import com.learn.springboot.chapter7.entity.Customer;
import com.learn.springboot.chapter7.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 13:22
 **/
@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;
    private String addr;
    private String password;

    @GetMapping("list")
    public List<Customer> list(){
        return customerService.list();
    }
    @GetMapping("list/{type}")
    public List<Customer> list(@PathVariable Integer type){
        return customerService.listByDb(type);
    }
    @GetMapping("addr/{addr}")
    public List<Customer> getByAddr(@PathVariable String addr){
        System.out.println(addr+"-----------------------");
        return customerService.getByAddr(addr);
    }


    @GetMapping("password/{password}")
    public List<Customer> getByPassword(@PathVariable String password){
        return customerService.getByPassworda(password);
    }
}
