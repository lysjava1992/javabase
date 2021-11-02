package com.test.securtiy.service;

import com.test.securtiy.model.CustomUser;

import java.util.List;

public interface UserService {
    CustomUser save(CustomUser user);
    CustomUser findById(Integer id);
    List<CustomUser> findAll();
    boolean delete(Integer id);
}
