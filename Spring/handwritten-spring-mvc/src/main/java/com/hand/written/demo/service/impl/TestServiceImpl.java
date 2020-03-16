package com.hand.written.demo.service.impl;

import com.hand.written.demo.service.TestService;
import com.hand.written.spring.formwork.annotation.Service;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName TestServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:59
 * @Version 1.0
 **/
@Service
public class TestServiceImpl implements TestService {
    @Override
    public String test(String str) {
      //  System.out.println("【TestServiceImpl】===============");
        return "TestServiceImpl:【"+str+"】";
    }
}
