package com.handbook.zookeeper.client;

import com.handbook.zookeeper.base.service.SimpleCount;
import com.handbook.zookeeper.base.service.SimpleDo;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   通过代理获取
 *    实际是 1）zk远程调用获取地址
 *           2）远程方法调用返回
 * @ClassName ClientApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 12:09
 * @Version 1.0
 **/
public class ClientApp {
    public static void main(String[] args) throws IOException {
        RpcZKProxy proxy=new RpcZKProxy();

        SimpleCount sc= proxy.remoteProxy(SimpleCount.class);
        SimpleDo sd=proxy.remoteProxy(SimpleDo.class);
        System.out.println(sc.add(1,2));
        System.out.println(sd.doSomeThing());
        System.out.println(sd.doSomeThing("King"));
    }
}
