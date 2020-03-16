package com.learn.spring.base.chapter4.service;

import com.learn.spring.base.chapter4.bean.Consumer;
import com.learn.spring.base.chapter4.resources.LoginRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 * @ClassName LoginServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 11:08
 * @Version 1.0
 **/
@Service(value = "loginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    LoginRepository loginRepository;

    public void login(Consumer consumer) {
         System.out.println("登录名："+consumer);
         Consumer consumer1=loginRepository.find(consumer.getName());
         System.out.println("校验名："+consumer1);
    }
}
