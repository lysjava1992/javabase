package com.test.securtiy.controller;

import com.test.securtiy.model.CustomUser;
import com.test.securtiy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/find")
    public List<CustomUser> findAll(){
        return userService.findAll();
    }

    @PostMapping()
    public CustomUser save(CustomUser user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userService.save(user);
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable("id") int id){

        return userService.delete(id);
    }
}
