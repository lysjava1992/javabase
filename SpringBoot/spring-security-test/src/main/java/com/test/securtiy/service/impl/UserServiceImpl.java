package com.test.securtiy.service.impl;

import com.test.securtiy.model.CustomUser;
import com.test.securtiy.repository.UserRepository;
import com.test.securtiy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public CustomUser save(CustomUser user) {
        return userRepository.save(user);
    }

    @Override
    public CustomUser findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<CustomUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean delete(Integer id) {
        userRepository.deleteById(id);
        return true;
    }
}
