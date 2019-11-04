package com.handbook.rpc.client;

import com.handbook.rpc.base.RpcClass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcInvocationHandler
 *  此处是向服务端发起调用的代码
 *  实质还是对请求的调用进行封装RpcClass
 *  socket远程调用服务端的方法 返回结果
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:37
 * @Version 1.0
 **/
public class RpcInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RpcInvocationHandler(String host, int port) {
        this.host=host;
        this.port=port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcClass rpcClass=new RpcClass();
        rpcClass.setClassName(method.getDeclaringClass().getName());
        rpcClass.setMethodName(method.getName());
        rpcClass.setParams(args);
        TCPTransport transport=new TCPTransport(host,port);
        return transport.sendMsg(rpcClass);
    }
}
