package com.handbook.zookeeper.provide;

import com.handbook.zookeeper.base.service.SimpleCount;
import com.handbook.zookeeper.base.service.SimpleDo;
import com.handbook.zookeeper.provide.impl.CountImpl;
import com.handbook.zookeeper.provide.impl.SimpleDoSay;
import com.handbook.zookeeper.provide.tcp.TCPServer;
import com.handbook.zookeeper.provide.zk.ZKPublish;

import java.io.IOException;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *   服务端发布过程包括两个动作
 *     1.向ZK注册中心注册
 *     2.启动socket服务器
 *
 * @ClassName ServerApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:50
 * @Version 1.0
 **/
public class ServerApp {
    public static void main(String[] args) {
        SimpleCount sc=new CountImpl();
        SimpleDo sd=new SimpleDoSay();
        ServicePublish sp= null;
        try {
            sp = new ServicePublish("zk","127.0.0.1:8080");
            sp.bind(sc,sd);
            sp.publish();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
