package com.handbook.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @description:      Zookeeper  分布式协调服务
 *   分布式架构下： 服务越来越多，服务的新增 宕机 对应而来是各服务的地址维护相对越来越复杂；zookeeper动态注册和获取服务信息的中心
 *            配置维护   发布/订阅 ：   解决服务器集群的配置文件管理
 *           域名服务    服务映射表：   客户端不必维护资源路径 *****
 *           分布式同步 watcher监听：   负责协调一个请求需要多个不同服务器来处理时的协调
 *           集群管理    故障管理
 *
 *     <dependency>
 *        <groupId>org.apache.zookeeper</groupId>
 *         <artifactId>zookeeper</artifactId>
 *            <version>3.5.5</version>
 *     </dependency>
 *     原生客户端
 * @author: Mr.Luan
 * @create: 2019-11-01 10:02
 **/
public class ZookeeperSimpleClient {
    /**
     * 集群地址 ，
     */
    private String ips;
    private ZooKeeper zooKeeper;
    private CountDownLatch downLatch=new CountDownLatch(1);
    public ZookeeperSimpleClient(String ips) throws Exception {
        this.ips = ips;
        //连接超时时间   //注册watcher事件
         zooKeeper=new ZooKeeper(ips,
                 1000,
                 new Watcher() {
                     public void process(WatchedEvent watchedEvent) {
                        if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
                            //注册Watcher事件，监听连接，放在主线程
                            downLatch.countDown();
                        }
                     }
                 });
         downLatch.await();
         System.out.println("============连接服务器成功=============");
    }

    /**
     *  添加一个节点
     *  开放权限 持久化的节电
     *  node   /x/xx/xxx
     *    只有/x/xx存在 /xxx才能创建
     *    层级结构 一层一层创建
     * @param node
     * @param msg
     * @throws Exception
     */
    public void addNode(String node,String msg) throws Exception{
        zooKeeper.create(node,msg.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("节点创建完毕");
    }

    public Stat getNode(String path) throws Exception{
        Stat stat=new Stat();
        byte[] bytes=zooKeeper.getData(path,null,stat);
        System.out.println(new String(bytes));
        return stat;
    }
    public void updateNode(String path,String msg,Stat stat) throws KeeperException, InterruptedException {
        zooKeeper.setData(path,msg.getBytes(),stat.getVersion());
        System.out.println("修改完毕");
    }
    public void deleteNode(String path,int version) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,version);
        System.out.println(path+"；版本号："+version +"   删除完毕");
    }
    public void close() throws InterruptedException {
        zooKeeper.close();
        System.out.println("============服务器已关闭=============");
    }

    /**
     * 绑定watcher事件
     */
    public Stat boundWatch(String path) throws KeeperException, InterruptedException {
        Stat stat=zooKeeper.exists(path,
                new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        System.out.println("事件监控: "+watchedEvent.getType()+"---->"+watchedEvent.getPath());
                        try {
                            //继续绑定
                            boundWatch(watchedEvent.getPath());
                        } catch (KeeperException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return stat;
    }

    public static void main(String[] args) throws Exception {
        ZookeeperSimpleClient client=new ZookeeperSimpleClient("192.168.1.162:2181");
        //添加
        client.addNode("/java_1","节点说明：version_1");
        //绑定
        Stat stat=  client.boundWatch("/java_1");
        //修改
        client.updateNode("/java_1","修改信息：version_2",stat);
        Thread.sleep(5000);
        //修改
        stat=  client.getNode("/java_1");
        client.updateNode("/java_1","修改信息：version_3",stat);
        Thread.sleep(5000);

        //读取
        stat=  client.getNode("/java_1");
        Thread.sleep(5000);
        //删除
        client.deleteNode("/java_1",stat.getVersion());
        //关闭
        client.close();
    }
}
