package com.handbook.zookeeper.client;

import com.handbook.zookeeper.base.ClassInfo;
import com.handbook.zookeeper.client.tcp.ServiceInvocation;
import com.handbook.zookeeper.client.zk.ServiceDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RemoteInvocationHandler
 *   具体的方法调用分两步
 *    1.从ZK注册中心获取地址
 *    2.远程调用
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 12:18
 * @Version 1.0
 **/
public class RemoteInvocationHandler implements InvocationHandler {
      private ServiceDiscovery serviceDiscovery;
    public RemoteInvocationHandler(ServiceDiscovery sd) {
        this.serviceDiscovery=sd;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
         String ipAddress=serviceDiscovery.discoveryService(method.getDeclaringClass().getName());
         if(ipAddress!=null){
             ClassInfo classInfo=new ClassInfo();
             classInfo.setClassName(method.getDeclaringClass().getName());
             classInfo.setMethodName(method.getName());
             classInfo.setParams(args);
             ServiceInvocation invocation=new ServiceInvocation(classInfo,ipAddress);
             return invocation.invoke();
         }
        return null;
    }
}
