package com.handbook.rpc.provide;

import com.handbook.rpc.base.RpcSimple;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ServerApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 18:05
 * @Version 1.0
 **/
public class ServerApp {
    public static void main(String[] args) {
        RpcSimple rs=new HelloImpl();
        RpcServer server=new RpcServer();
        server.publish(8001,rs);
    }
}
