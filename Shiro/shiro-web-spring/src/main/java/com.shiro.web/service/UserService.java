package com.shiro.web.service;

import com.shiro.web.model.User;

import java.util.List;

public interface UserService {

    User findByName(String name);
    List<User> find();
    User  add(User user);
    User  update(User user);
    void  delete(int id);
}
