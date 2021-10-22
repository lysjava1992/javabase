package com.learn.springboot.chapter11.controller;

import com.learn.springboot.chapter11.model.Customer;
import com.learn.springboot.chapter11.model.Result;
import com.learn.springboot.chapter11.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 17:02
 **/

@RequestMapping("cc")
@RestController
public class CustomerController {

     @Autowired
    CustomerRepository customerRepository;
    @GetMapping
    public Result get(){
         Result result=new Result(200,"查询成功",customerRepository.findAll());
         return result;
    }

   @PostMapping
    public Result add(Customer customer){
        Result result=new Result(200,"添加用户成功",customerRepository.save(customer));
        return result;
    }

    @PutMapping
    public Result put(Customer customer){
        Result result=new Result(200,"修改用户成功",customerRepository.save(customer));
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        customerRepository.deleteById(id);
        Result result=new Result(200,"删除成功");
        return result;
    }
}
