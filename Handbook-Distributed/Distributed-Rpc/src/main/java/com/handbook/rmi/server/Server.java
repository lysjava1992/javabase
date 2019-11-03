package com.handbook.rmi.server;

import com.handbook.rmi.base.RmiSimple;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Server
 *  负责发布服务
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 16:34
 * @Version 1.0
 **/
public class Server {
    public static void main(String[] args) {
        try {
            RmiSimple rs=new RmiSimpleImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/hello",rs);
            System.out.println("服务已发布");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
