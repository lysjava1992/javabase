package com.handbook.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import java.util.List;

/**
 * @description: Curator——Zookeeper客户端 ，对原生zookeeper进行了封装
 *          重试机制
 *          连接状态监控
 *          zk客户端实例管理
 *          各种使用场景支持
 *
 *   Curator包含了几个包：
 *      curator-framework：对zookeeper的底层api的一些封装
 *      curator-client：提供一些客户端的操作，例如重试策略等
 *      curator-recipes：封装了一些高级特性，如：Cache事件监听、选举、分布式锁、分布式计数器、分布式Barrier等
 *
 *     PathChildCache 监听一个节点下子节点的创建、删除、更新
 *     NodeCache  监听一个节点的更新和创建事件
 *    TreeCache  综合PatchChildCache和NodeCache的特性
 *  同步
 * @author: Mr.Luan
 * @create: 2019-11-01 13:36
 **/
public class CuratorClient {
    private String ips;
    private  CuratorFramework curatorFramework;
    public CuratorClient(String s) {
            this.ips=s;
            //超时时间 重试策略 工作空间 即第一级路径
      curatorFramework= CuratorFrameworkFactory.builder()
                         .connectString(ips)
                         .sessionTimeoutMs(4000)
                         .retryPolicy(new ExponentialBackoffRetry(1000,3))
                         .namespace("curator")
                         .build();
      curatorFramework.start();
      System.out.println("============连接ZK服务器成功===========");
    }

    /**
     *  实际节点路径 namespace/path
     *  creatingParentsIfNeeded() 多级节点 上级不存在会创建
     *  CreateMode.PERSISTENT  持久化节点
     * @param path
     * @param msg
     */
   public void addNode(String path,String msg) throws Exception {
        curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(path,msg.getBytes());
        System.out.println("节点创建完毕");

   }

    /**
     * 读取数据
     *  byte[] bytes=  curatorFramework.getData().forPath("/");
     *  读取数据返回Stat
     *
     * @param path
     * @return
     */
   public Stat readNode(String path) throws Exception {
       Stat stat=new Stat();
       byte[] arr=curatorFramework.getData()
                       .storingStatIn(stat)
                       .forPath(path);
       System.out.println(new String(arr)+" :"+stat.getVersion());
       return stat;

   }

    /**
     * 更新 指定版本号
     * @param version
     * @param path
     * @param msg
     * @return
     * @throws Exception
     */
    public  Stat updateNode(int version,String path,String msg) throws Exception {

        Stat stat=curatorFramework.setData()
                       .withVersion(version)
                       .forPath(path,msg.getBytes());
        return stat;
    }

    /**
     * 删除 只能删除子节点
     * @param path
     * @throws Exception
     */
    public void delete(String path) throws Exception {
        curatorFramework.delete().forPath(path);
    }

    /**
     * true 存在
     * @param path
     * @return
     * @throws Exception
     */
    public boolean isExist(String path) throws Exception {
       Stat stat= curatorFramework.checkExists().forPath(path);
       return stat!=null;
    }

    /**
     * 当前节点的下一级子节点
     * @param name
     * @throws Exception
     */
    public void list(String name) throws Exception {
       List<String> list= curatorFramework.getChildren().forPath(name);
      for (String str:list){
          System.out.print(str+"  ");
      }
      System.out.println();
    }
    /**
     * 注册监听事件 非一次性
     * @param path
     * @throws Exception
     */
   public void  boundWatch(String path) throws Exception {
       final NodeCache nodeCache = new NodeCache(curatorFramework,path);
       nodeCache.start();
       nodeCache.getListenable().addListener(new NodeCacheListener() {

           public void nodeChanged() throws Exception {
               System.out.println("监听事件触发 :节点发生变化" );
           }
       });
   }

   public void close(){
       curatorFramework.close();
       System.out.println("============ZK客户端关闭==========");
   }
    public static void main(String[] args) throws Exception {
        CuratorClient client=new CuratorClient("192.168.1.162");
        String node="/java/demo/zk_6";

        System.out.println(node+"是否存在： "+client.isExist(node));

        client.addNode(node,"初始信息_v1.0");

        System.out.println(node+"是否存在： "+client.isExist(node));

        client.boundWatch(node);

        Stat stat=client.readNode(node);

        client.updateNode(stat.getVersion(),node,"更新信息_v2");

        Thread.sleep(5000);

        stat=client.readNode(node);

        client.updateNode(stat.getVersion(),node,"更新信息_v3");

        Thread.sleep(Integer.MAX_VALUE);
        client.close();
    }
}
