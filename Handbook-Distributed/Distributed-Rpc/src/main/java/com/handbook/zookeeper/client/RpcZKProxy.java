package com.handbook.zookeeper.client;

import com.handbook.zookeeper.base.service.SimpleCount;
import com.handbook.zookeeper.client.tcp.ServiceInvocation;
import com.handbook.zookeeper.client.zk.ServiceDiscovery;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcProxy
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 12:11
 * @Version 1.0
 **/
public class RpcZKProxy {
    private ServiceDiscovery sd;
    public RpcZKProxy() throws IOException {
        this.sd=new ServiceDiscovery();
    }

    public  <T> T remoteProxy(Class<T> interfaceClass){

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                               new Class[]{interfaceClass},new  RemoteInvocationHandler(sd));
    }
}
