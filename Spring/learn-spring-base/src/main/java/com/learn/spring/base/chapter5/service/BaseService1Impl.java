package com.learn.spring.base.chapter5.service;

import org.springframework.stereotype.Service;

/**
 *切入点
 *
 * @ClassName BaseServiceImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 14:08
 * @Version 1.0
 **/
@Service
public class BaseService1Impl implements BaseService1 {
    public void doMethod1() {
        System.out.println("方法一执行");
    }

    public void doMethod2() {
        System.out.println("方法二执行");
    }
}
