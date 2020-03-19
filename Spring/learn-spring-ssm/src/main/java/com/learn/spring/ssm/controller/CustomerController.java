package com.learn.spring.ssm.controller;

import com.learn.spring.ssm.entity.Customer;
import com.learn.spring.ssm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-19 14:10
 **/
@RequestMapping("/user")
@Component
public class CustomerController {
      @Autowired
       private CustomerService customerService;
    @GetMapping("")
    public String loginPage(){
        return "login";
    }

    @PostMapping("login")
    public String login(HttpServletRequest request,Customer customer, Model model) {
        Customer dbCustomer=customerService.findOne(customer);
        if(dbCustomer==null){
            model.addAttribute("msg","无效用户");
        }else if(!dbCustomer.getPassword().equals(customer.getPassword())){
            model.addAttribute("msg","密码错误");
        }else {
            request.getSession().setAttribute("username",customer.getUsername());
            return "redirect:/";
        }

        return "login";
    }

    @ResponseBody
    @GetMapping("add")
    public Customer add(Customer customer){
          customer=customerService.add(customer);
          return customer;
    }

    @ResponseBody
    @GetMapping("del")
    public String delet(Long id){
        customerService.delete(id);
        return "ok";
    }

    @ResponseBody
    @GetMapping("dels")
    public String delets(Long[] ids){
        customerService.deletes(ids);
        return "ok";
    }
    @ResponseBody
    @GetMapping("list")
    public List<Customer> add(){
        List<Customer> customers=customerService.findAll();
        return customers;
    }

}
