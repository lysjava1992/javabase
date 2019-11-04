package com.handbook.zookeeper.client.zk;

import com.handbook.zookeeper.base.ZKConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ServiceDiscovery
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 12:31
 * @Version 1.0
 **/
public class ServiceDiscovery {
    private ZKConfig zkConfig;
    private CuratorFramework curatorFramework;
    List<String> urls;
    public ServiceDiscovery() throws IOException {
        this.zkConfig=new ZKConfig("zk");
        this.curatorFramework= CuratorFrameworkFactory.builder()
                         .connectString(zkConfig.serverIps)
                         .sessionTimeoutMs(zkConfig.timeOut)
                        .retryPolicy(new ExponentialBackoffRetry(1000,3))
                        .build();
        curatorFramework.start();
    }

    public String discoveryService(String name) {
        String node=zkConfig.repositoryName+"/"+name;
        try {
            urls=curatorFramework.getChildren()
                            .forPath(node);
            if(urls==null&&urls.isEmpty()){
                throw new RuntimeException("暂未发现服务");
            }
            if (urls.size()==1){
                return urls.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
      return  loadBalance(urls);
    }

    private String loadBalance(List<String> urls) {
        Random rand=new Random();

        int randNumber =rand.nextInt(urls.size()-1);
        return urls.get(randNumber);
    }

}
