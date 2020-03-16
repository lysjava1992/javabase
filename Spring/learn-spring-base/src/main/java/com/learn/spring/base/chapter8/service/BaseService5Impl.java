package com.learn.spring.base.chapter8.service;

import org.springframework.stereotype.Service;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BaseService5Impl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 16:06
 * @Version 1.0
 **/
@Service
public class BaseService5Impl implements BaseService5 {
    public void add() {
         System.out.println("添加操作");
    }

    public void select() {
        System.out.println("查询操作");
    }

    public void remove() {
        System.out.println("删除操作");
    }
}
