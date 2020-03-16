package com.learn.spring.base.chapter6.service;

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
public class BaseService2Impl implements BaseService2 {


    public void add() {
        System.out.println("【BaseService2】执行添加方法==需要通知");
    }

    public void find() {
        System.out.println("【BaseService2】执行查询方法==无通知");
    }
}
