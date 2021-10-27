package com.shiro.web.service.impl;

import com.shiro.web.model.User;
import com.shiro.web.service.UserService;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    private static AtomicInteger atomicInteger=new AtomicInteger(0);
    private static List<User> USERS_DATA=new ArrayList();
    static {
        User user= new User(atomicInteger.getAndIncrement(),"king","123456");
        user.setRole("admin");
        Set<String> stringSet=new HashSet<>();
        stringSet.add("user:*");
        user.setPermissions(stringSet);
        USERS_DATA.add(user);

        User user2=new User(atomicInteger.getAndIncrement(),"LiSi","123456");
        user2.setRole("user");
        stringSet=new HashSet<>();
        stringSet.add("user:find");
        stringSet.add("user:update");
        user.setPermissions(stringSet);
        USERS_DATA.add(user2);
    }
    @Override
    public User findByName(String name) {
        for (User user : USERS_DATA) {
            if(user.getUsername().equals(name)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> find() {
        return new ArrayList<User>(USERS_DATA);
    }

    @Override
    public User add(User user) {
        user.setId(atomicInteger.getAndIncrement());
        USERS_DATA.add(user);
        return user;
    }

    @Override
    public User update(User user) {
        for (User us : USERS_DATA) {
            if(user.getId()==us.getId()){
                us.setPassword(us.getPassword());
                return user;
            }
        }
        return user;
    }

    @Override
    public void delete(int id) {
        for (User user : USERS_DATA) {
             if(user.getId()==id){
                 USERS_DATA.remove(user);
             }
        }
    }
}
