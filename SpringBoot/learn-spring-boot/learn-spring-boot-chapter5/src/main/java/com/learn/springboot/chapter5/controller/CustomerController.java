package com.learn.springboot.chapter5.controller;

import com.learn.springboot.chapter5.entity.Customer;
import com.learn.springboot.chapter5.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:27
 **/
@Api(tags = "用户管理接口")
@RequestMapping("user")
@RestController
public class CustomerController {

    @Autowired
    CustomerService service;

    @PostMapping("/")
    @ApiOperation("添加用户的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", defaultValue = "李四"),
            @ApiImplicitParam(name = "address", value = "用户地址", defaultValue = "深圳", required = true)
    })
    public Customer addUser(String username,  String address) {
              Customer customer=new Customer();
              customer.setUsername(username);
              customer.setAddr(address);
              customer.setPassword("123456");
              customer.setCreateTime("2020-03-26 16:46:01");
        return service.add(customer);
    }

    @GetMapping("/list/{type}")
    @ApiOperation("查询所有用户")
    @ApiImplicitParam(name = "type", value = "数据库编号", defaultValue = "1")
    public List<Customer> getUsers(@PathVariable Integer type ) {
           List<Customer> customers=new ArrayList<>();
        return  service.selectList(type);
    }


    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", defaultValue = "1", required = true)
    public Customer getUserById(@PathVariable Integer id) {

        return  service.selectById(id);
    }

    @PutMapping("")
    @ApiOperation("更新用户的接口")
    public Customer updateUserById(@RequestBody Customer user) {
        return  service.update(user);
    }
}
