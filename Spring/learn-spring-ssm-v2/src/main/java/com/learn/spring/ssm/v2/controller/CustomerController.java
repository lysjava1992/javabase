package com.learn.spring.ssm.v2.controller;

import com.learn.spring.ssm.v2.entity.Customer;
import com.learn.spring.ssm.v2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 13:01
 **/
@RequestMapping("user")
@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("add")
    public Customer add(Customer customer){
        customer=customerService.add(customer);
        return customer;
    }


    @GetMapping("del")
    public String delet(Long id){
        customerService.delete(id);
        return "ok";
    }


    @GetMapping("dels")
    public String delets(Long[] ids) throws Exception {
        customerService.deletes(ids);
        return "ok";
    }

    @GetMapping("list")
    public List<Customer> add(){
        List<Customer> customers=customerService.findAll();
        return customers;
    }
}
