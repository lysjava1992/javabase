package com.handbook.rmi.client;

import com.handbook.rmi.base.RmiSimple;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Client
 *  服务调用方 需要远程调用server提供的服务
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 16:29
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        try {
            RmiSimple rs= (RmiSimple) Naming.lookup("rmi://127.0.0.1/hello");
          System.out.println(rs.hello("King"));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
