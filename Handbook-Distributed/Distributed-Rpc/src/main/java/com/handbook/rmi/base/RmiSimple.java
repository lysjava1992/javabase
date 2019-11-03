package com.handbook.rmi.base;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * RMI ( remote method invocation) 远程方法调用，
 * 一种用于远程过程调用的应用程序编程接口，
 * 是纯 java 的 网络分布式应用系统的核心解决方案之一:
 *
 *  当前并不常用
 *  RmiSimple 为接口层
 *  在实际中 client 和 server 包为两个独立的项目
 *  base为公共接口，两个包中都会存在
 *     client 和 server 都可以直接使用base
 *      client 和 server是无法直接调用的
 *
 *   extends Remote
 * @ClassName RmiSimple
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 16:24
 * @Version 1.0
 **/
public interface RmiSimple extends Remote{
    String hello(String msg)throws RemoteException;
}
