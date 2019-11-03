package com.handbook.zookeeper.provide.impl;

import com.handbook.zookeeper.base.service.SimpleDo;
import com.handbook.zookeeper.provide.annotation.RpcAnnotation;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SimpleDoSay
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 14:22
 * @Version 1.0
 **/
@RpcAnnotation(SimpleDo.class)
public class SimpleDoSay implements SimpleDo {
    public String doSomeThing() {
        System.out.println("just do =====");
        return "Just do .......";
    }

    public String doSomeThing(String msg) {
        return "Hello !!!"+msg;
    }
}
