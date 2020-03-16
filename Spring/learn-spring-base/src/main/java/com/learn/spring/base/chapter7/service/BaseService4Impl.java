package com.learn.spring.base.chapter7.service;

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
public class BaseService4Impl implements BaseService4 {


    public void add() {
        System.out.println("执行添加方法");
    }

    public void find() {
        System.out.println("执行查询方法");
    }

    public void delete() { System.out.println("执行删除方法"); }
}
