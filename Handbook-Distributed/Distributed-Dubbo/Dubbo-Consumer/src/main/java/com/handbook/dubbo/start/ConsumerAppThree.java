package com.handbook.dubbo.start;

import com.handbook.dubbo.api.ServiceOne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Mr.Luan
 * zookeepr 注册中心
 *  dubbo 负载均衡
 *      默认情况下dubbo采用随机算法 Random LoadBalance               不用配置
 *
 *            轮询       RoundRobin LoadBalance          配置        loadbalance="roundrobin"
 *           轮询+权重   在轮询的基础上 服务端在服务接口设置不同      weight="1000"
 *
 *    配置可以基于方法，即具体的方法调用  也可以基于服务即接口
 *  支持版本控制
 * @create: 2019-11-04 13:06
 **/
public class ConsumerAppThree {
    public static void main(String[] args) throws InterruptedException {
        int index =18;
        for (int i=0;i<index;i++){
            new Thread(new Remoting()).start();
        }
    }
  static class Remoting implements Runnable  {
      public void run() {
          ApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/client2.xml");
          ServiceOne so= (ServiceOne) context.getBean("serviceOne");
         // ServiceOne so2= (ServiceOne) context.getBean("serviceTwo");
          System.out.println(so.sayHello("King"));
        //  System.out.println(so2.sayHello("King"));
      }
    }
}
