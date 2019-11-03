package com.handbook.zookeeper.provide;

import com.handbook.zookeeper.base.ZKConfig;
import com.handbook.zookeeper.base.service.SimpleCount;
import com.handbook.zookeeper.provide.annotation.RpcAnnotation;
import com.handbook.zookeeper.provide.tcp.TCPServer;
import com.handbook.zookeeper.provide.zk.ZKPublish;
import org.apache.curator.framework.CuratorFramework;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ServicePulish
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:55
 * @Version 1.0
 **/
public class ServicePublish {
    private ZKConfig config;
    private ZKPublish zkPublish;
    private TCPServer tcpServer;
    private Map<String,Object> map=new HashMap<String, Object>();
    private String ip;
    public ServicePublish(String properties,String ip) throws Exception {
        this.config=new ZKConfig(properties);
        this.zkPublish=new ZKPublish(config);
        this.tcpServer=new TCPServer(ip);
        this.ip=ip;
    }


    /***
     * 绑定 名称与实际服务类的关系
     * 扫描注解
     * 获得实例接口名称
     * @param objects
     */
    public void bind(Object ... objects) {
        for (Object o:objects){
            RpcAnnotation rpcAnnotation=o.getClass().getAnnotation(RpcAnnotation.class);
            map.put(rpcAnnotation.value().getName(),o);
        }
    }

    /**
     * 发布服务
     *    1.向zk注册中心注册地址
     *    2.启动Socket服务器
     */
    public void publish() {
        try {
            zkPublish.register(map.keySet(),ip);
            tcpServer.start(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
