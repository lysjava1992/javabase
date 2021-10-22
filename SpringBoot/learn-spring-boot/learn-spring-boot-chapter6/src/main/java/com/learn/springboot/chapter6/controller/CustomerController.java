package com.learn.springboot.chapter6.controller;
import com.learn.springboot.chapter6.entity.Customer;
import com.learn.springboot.chapter6.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:27
 **/
@RequestMapping("user")
@RestController
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/")
    public Customer addUser(String username, String address) {
              Customer customer=new Customer();
              customer.setUsername(username);
              customer.setAddr(address);
              customer.setPassword("123456dd");
              customer.setCreateTime("2020-03-27 16:46:01");
        return service.add(customer);
    }

    @GetMapping("/list/{type}")
    public List<Customer> getUsers(@PathVariable Integer type ) {
           List<Customer> customers=new ArrayList<>();
        return  service.selectList(type);
    }


    @GetMapping("/{id}")
    public Customer getUserById(@PathVariable Integer id) {

        return  service.selectById(id);
    }

    @PutMapping("")
    public Customer updateUserById(@RequestBody Customer user) {
        return  service.update(user);
    }
}
