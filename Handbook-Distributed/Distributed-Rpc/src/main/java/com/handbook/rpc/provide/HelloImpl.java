package com.handbook.rpc.provide;

import com.handbook.rpc.base.RpcSimple;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HelloImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:17
 * @Version 1.0
 **/
public class HelloImpl implements RpcSimple {
    public String hello(String msg) {
        return "Hello! "+msg;
    }
}
