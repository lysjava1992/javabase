package com.handbook.rmi.server;

import com.handbook.rmi.base.RmiSimple;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * RmiSimpleImpl 是RmiSimple的一个实现类 可以当做是服务的提供方
 *
 *           extends UnicastRemoteObject
 *           表示是一个要发布的远程对象
 * @ClassName RmiSimpleImpl
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 16:28
 * @Version 1.0
 **/
public class RmiSimpleImpl extends UnicastRemoteObject implements RmiSimple {
    protected RmiSimpleImpl() throws RemoteException {
    }

    public String hello(String msg) {
        return "Hello! "+msg;
    }
}
