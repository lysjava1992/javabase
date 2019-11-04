package com.handbook.zookeeper.provide.impl;

import com.handbook.zookeeper.base.service.SimpleCount;
import com.handbook.zookeeper.provide.annotation.RpcAnnotation;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CountImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:51
 * @Version 1.0
 **/
@RpcAnnotation(SimpleCount.class)
public class CountImpl implements SimpleCount {
    public int add(Integer a, Integer b) {
        return a+b;
    }

    public int sub(Integer a, Integer b) {
        return a-b;
    }
}
