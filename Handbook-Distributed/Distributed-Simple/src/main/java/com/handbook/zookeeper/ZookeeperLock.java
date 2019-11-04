package com.handbook.zookeeper;

import com.sun.org.apache.bcel.internal.generic.FADD;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import javax.print.attribute.standard.NumberUp;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ZookeeperLock
 *   zookeeper 实现分布式锁
 *     1） zookeeper 上的lock节点为根节点
 *     2）各个客户端在lock节点下建立有序临时节点（zookeeper自带）
 *     3）每个客户端只需监控比自己节点小1的节点
 *     4）自己监控的节点发生变化，则判断自己是否是当前最小的节点
 *     5）是则拿锁运行，不是则继续等待
 *
 *      public void process(WatchedEvent watchedEvent) 负责处理监听
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 14:02
 * @Version 1.0
 **/
public class ZookeeperLock implements Lock ,Watcher{
    /**
     * 集群地址
     */
    private String ips;
    /**
     * 根节点
     */
    private String LOCK_ROOT="/locks";
    /**
     * 需要监控等待的临时锁节点
     */
    private String LOCK_WAIT;
    /**
     * 当前节点
     */
    private String LOCK_CURRENT;
    private ZooKeeper zooKeeper;
    /**
     * 阻塞
     */
    private CountDownLatch countDownLatch;
    /**
     * 初始化 连接zookeeper 创建根节点
     * @param ips
     */
    public ZookeeperLock(String ips) throws Exception {
        this.ips = ips;
       zooKeeper=new ZooKeeper(ips,4000,this);
       Stat stat=zooKeeper.exists(LOCK_ROOT, false);
       if(stat==null){
           //创建根节点  权限开放 持久化
           zooKeeper.create(LOCK_ROOT,"0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
       }
    }

    /**
     * 拿锁
     */
    @Override
    public void lock() {
      if(tryLock()){
          return;
      }
        try {
            waitForLock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听起一个节点
     * 阻塞等待
     */
    private boolean waitForLock() throws InterruptedException, KeeperException {
        Stat stat=zooKeeper.exists(LOCK_WAIT,true);
        if(stat!=null){
            countDownLatch=new CountDownLatch(1);
            countDownLatch.await();
        }
     return true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    /**
     * 核心 操作锁
     *  1）给客户端/线程 在/lock 节点下创建临时有序节点
     *  2）获取/lock 节点下所有临时子节点
     *  3)排序
     *  4）判断 如果当前临时节点是最小的则返回true 即拿到锁
     *    4.2）不随最小的则返回false;并记录当前节点的上一个节点，以便监控
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            // 1) 步骤一
            LOCK_CURRENT=zooKeeper.create(LOCK_ROOT+"/",
                              "0".getBytes(),
                               ZooDefs.Ids.OPEN_ACL_UNSAFE,
                               CreateMode.EPHEMERAL_SEQUENTIAL);
            //2)步骤二
            List<String>list=zooKeeper.getChildren(LOCK_ROOT,false);
            //3)步骤三 sortedSet
            SortedSet<String> sortedSet=new TreeSet<>();
            for (String str:list){
                sortedSet.add(LOCK_ROOT+"/"+str); }
            //4)    lessSet 比当前节点小的节点的集合

            SortedSet<String> lessSet=sortedSet.headSet(LOCK_CURRENT);

            if(lessSet.isEmpty()){
                //空 当前节点为最小节点
                return true;
            }
            LOCK_WAIT=lessSet.last();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock() {
        try {
            zooKeeper.delete(LOCK_CURRENT,-1);
            LOCK_WAIT= null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    /**
     * 监控节点发生变化
     * 取消当前线程的阻塞
     * @param watchedEvent
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if(countDownLatch!=null){
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        String ip1="192.168.1.108:2181";
        String ip2="192.168.1.110:2181";
        final int[] tall = {80};
        final CountDownLatch downLatch=new CountDownLatch(100);
        for (int i=0;i<100;i++){
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        downLatch.await();
                        // 要确保一个用户拿到资源才可以
                        ZookeeperLock lock=null;
                        if(index%2==1){
                            lock=new ZookeeperLock(ip1);
                        }else {
                            lock=new ZookeeperLock(ip2);
                        }
                        //拿锁
                        lock.lock();
                        if(tall[0] >=1){
                            Thread.sleep(1000);
                            tall[0] = tall[0] -1;
                            System.out.println("用户"+ index +" 抢购成功；库存"+ tall[0]);
                        }else {
                            System.out.println("用户"+ index +" 抢购失败；库存"+ tall[0]);
                        }
                        lock.unlock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
            downLatch.countDown();
        }
    }
}
