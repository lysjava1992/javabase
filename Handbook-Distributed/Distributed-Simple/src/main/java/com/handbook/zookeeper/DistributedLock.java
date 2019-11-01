package com.handbook.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-01 16:25
 **/
public class DistributedLock implements Lock,Watcher {
    private ZooKeeper zk=null;
    private String ROOT_LOCK="/locks"; //定义根节点
    private String WAIT_LOCK; //等待前一个锁
    private String CURRENT_LOCK; //表示当前的锁
    private CountDownLatch countDownLatch;

    public DistributedLock() {
        try {
            zk=new ZooKeeper("192.168.1.162:2181,",
                    4000,
                    this);
            Thread.sleep(30000);
            //判断节点是否存在
            Stat stat=zk.exists(ROOT_LOCK,false);
            //不存在 创建
            if(stat==null){
                System.out.println("创建"+ROOT_LOCK);
                zk.create(ROOT_LOCK,"0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void lock() {
        if(this.tryLock()){
            System.out.println(Thread.currentThread().getName()+"->"+CURRENT_LOCK+"->获得锁成功");
            return;
        }
        try {
            waitForLock(WAIT_LOCK);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean waitForLock(String pre) throws KeeperException, InterruptedException {
        //监听上一个节点
        Stat stat=zk.exists(pre,true);
        if(stat!=null){
            System.out.println(Thread.currentThread().getName()+"->等待锁"+ROOT_LOCK+"/"+pre+"释放");
            countDownLatch=new CountDownLatch(1);
            countDownLatch.await();
            System.out.println(Thread.currentThread().getName()+"->获得锁成功");
        }
        return true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        // 创建临时有序节点
        try {
            CURRENT_LOCK= zk.create(ROOT_LOCK+"/","0".getBytes(),ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println(Thread.currentThread().getName()+"->"+
                    CURRENT_LOCK+",尝试竞争锁");
            List<String> childrens=zk.getChildren(ROOT_LOCK,false);// 获取所有根节点
            SortedSet<String> sortedSet=new TreeSet<String>();
            for(String children :childrens){
                sortedSet.add(ROOT_LOCK+"/"+children);
            }
            //获取最小的节点
            String firstNode=sortedSet.first();
            SortedSet<String> lessTheMe=sortedSet.headSet(CURRENT_LOCK);
            //通过当前节点和子节点中最小的节点进行比较，如果相等，拿到锁
            if(CURRENT_LOCK.equals(firstNode)){
                return true;
            }
            if(!lessTheMe.isEmpty()){
                //获得比当前节点更小的最后一个节点，设置给WAIT_LOCK
                WAIT_LOCK =lessTheMe.last();
            }

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

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName()+"->释放锁"+CURRENT_LOCK);
        try {
            zk.delete(CURRENT_LOCK,-1);
            CURRENT_LOCK=null;
            zk.close();
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

    @Override
    public void process(WatchedEvent watchedEvent) {
        if(this.countDownLatch!=null){
            this.countDownLatch.countDown();
        }
    }
    public static void main(String[] args) throws IOException {
        final CountDownLatch countDownLatch=new CountDownLatch(10);

        for(int i=0;i<10;i++){
            new Thread(()->{
                try {
                    countDownLatch.await();
                    DistributedLock distributedLock=new DistributedLock();
                    distributedLock.lock();//获得锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"Thread-"+i).start();
            countDownLatch.countDown();
        }
        System.in.read();
    }
}