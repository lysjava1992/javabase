package com.shiro.web.controller;


import com.shiro.web.model.User;
import com.shiro.web.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresPermissions("user:find")
    @GetMapping("/find")
    public List<User> find() {
        return userService.find();
    }


    @RequiresRoles("admin")
    @GetMapping("/del")
    public void delete(int id) {
        userService.delete(id);
    }

    @RequiresPermissions("admin")
    @PostMapping("/add")
    public User save(User user) {
        return userService.add(user);
    }

    @RequiresPermissions("user:update")
    @PostMapping("/update")
    public User update(User user) {
        return userService.update(user);
    }

}
