package com.handbook.dubbo.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @description:
 *   在点对点通信中 需要调用端亲自来维护 服务提供端的地址
 *    在实际的分布式环境中服务的动态上线 线下 这些调用端都是不能动态感知的
 *    因此 Dubbo需要一个注册中心，
 *      包括Multicast、Zookeeper、Redis、Simple等。
 *      Dubbo官方推荐使用Zookeeper注册中心。
 *
 *   服务端需要把服务地址发布到zookeepr, 利用zookeeper的特性将路径设置为临时节点；服务断开（崩溃。关闭）节点消失
 *   客户端只需维护zookeeper集群的地址即可，从zookeeper获取地址，在本地缓存，并在zookeepr注册监听事件，节点发生变化，更新本地缓存
 *
 *   案例对应的配置文件  server2.xml
 *                       server2_2.xml
 *                       server2_3.xml
 *    三个配置文件发布的服务相同 端口不一样 演示dubbo的负载均衡
 * @author: Mr.Luan
 * @create: 2019-11-04 14:17
 **/
public class ProviderAppThree {
    public static void main(String[] args) throws IOException {
          ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/server2.xml");
        // ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/server2_2.xml");
       //  ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/server2_3.xml");
        context.start();
        System.out.println("---------服务已发布---------");
        System.in.read();
    }
}
