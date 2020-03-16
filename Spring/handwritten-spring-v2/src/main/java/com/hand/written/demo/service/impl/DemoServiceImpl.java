package com.hand.written.demo.service.impl;

import com.hand.written.demo.service.DemoService;
import com.hand.written.spring.formwork.annotation.Service;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DemoServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 10:29
 * @Version 1.0
 **/
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(String name) {
        return "Hello !"+name;
    }
}
