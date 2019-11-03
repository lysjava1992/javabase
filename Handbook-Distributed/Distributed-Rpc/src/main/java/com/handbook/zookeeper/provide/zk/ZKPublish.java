package com.handbook.zookeeper.provide.zk;

import com.handbook.zookeeper.base.ZKConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.awt.print.Pageable;
import java.util.Iterator;
import java.util.Set;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ZKPublish
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:57
 * @Version 1.0
 **/
public class ZKPublish {
    private ZKConfig config;
    private CuratorFramework curatorFramework;
    public ZKPublish(ZKConfig config) throws Exception {
        this.config=config;
        initConnect();
    }

    private void initConnect() throws Exception {
        this.curatorFramework= CuratorFrameworkFactory.builder()
                             .retryPolicy(new ExponentialBackoffRetry(1000,3))
                             .connectString(config.serverIps)
                             .sessionTimeoutMs(config.timeOut)
                             .build();
        curatorFramework.start();
       Stat stat= curatorFramework.checkExists().forPath(config.repositoryName);
       if(stat==null){
           curatorFramework.create()
                           .withMode(CreateMode.PERSISTENT)
                          .forPath(config.repositoryName);
       }
    }

    public void register(Set<String> names, String ip)throws Exception {
        if(!names.isEmpty()){
            Iterator iterator=names.iterator();
            while (iterator.hasNext()){
                String path=config.repositoryName+"/"+iterator.next();
                addNode(path);
                addTemporaryPath(path+"/"+ip);
            }
        }
        System.out.println("==================zk注册完毕==================");
    }

    private void addTemporaryPath(String s) throws Exception {
        curatorFramework.create()
                       .withMode(CreateMode.EPHEMERAL)
                      .forPath(s);

    }

    /***
     * 注册服务节点 永久
     * @param path
     */
    private void addNode(String path) throws Exception {
        Stat stat=curatorFramework.checkExists().forPath(path);
        if(stat==null){
            curatorFramework.create()
                            .withMode(CreateMode.PERSISTENT)
                           .forPath(path);
        }
    }
}
