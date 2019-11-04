package com.handbook.rpc.client;

import com.handbook.rpc.base.RpcSimple;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ClientApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:33
 * @Version 1.0
 **/
public class ClientApp {
    public static void main(String[] args) {
        RpcSimple rs=new RpcProxy().clientProxy(RpcSimple.class,"127.0.0.1",8001);
        System.out.println(rs.hello("RPC"));
    }
}
