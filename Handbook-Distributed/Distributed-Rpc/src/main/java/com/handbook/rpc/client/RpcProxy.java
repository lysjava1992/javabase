package com.handbook.rpc.client;

import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcProxy
 *  通过这个伪代理 使客户端屏蔽了对服务端的调用过程socket连接通信过程等
 *
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:33
 * @Version 1.0
 **/
public class RpcProxy {

    public <T> T clientProxy(final Class<T> interfaces,
                              final String host,final int port){
        return (T) Proxy.newProxyInstance(interfaces.getClassLoader(),
                new Class[]{interfaces},
                new RpcInvocationHandler(host,port));
    }
}
