package com.learn.springboot.chapter2.service;

import com.learn.springboot.chapter2.annotation.TransactionalService;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName EchoService
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/22 15:13
 * @Version 1.0
 **/
@TransactionalService(name = "echoService")
public class EchoService {
    public void  echo(){
        System.out.println("==========================");
        System.out.println("==========================");
        System.out.println("==========================");
    }

}
