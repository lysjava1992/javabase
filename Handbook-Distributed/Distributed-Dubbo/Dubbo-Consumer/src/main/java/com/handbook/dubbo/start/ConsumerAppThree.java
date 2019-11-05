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
 *           最少活跃调用数  LeastActive LoadBalance ，相同活跃数的随机，活跃数指调用前后计数差。
 *                            使慢的提供者收到更少请求，因为越慢的提供者的调用前后计数差会越大。
 *            一致性Hash  ConsistentHash LoadBalance ，相同参数的请求总是发到同一提供者。
 *              当某一台提供者挂时，原本发往该提供者的请求，基于虚拟节点，平摊到其它提供者，不会引起剧烈变动。
 *               配置可以基于方法，即具体的方法调用  也可以基于服务即接口
 *
 *  此案例对应配置文件 client1.xml
 *  dubbo 配置优先级 方法 > 接口 >全局
 *                   消费端>服务端
 * @create: 2019-11-04 13:06
 **/
public class ConsumerAppThree {
    public static void main(String[] args) throws InterruptedException {
        int index =1;
        for (int i=0;i<index;i++){
            new Thread(new Remoting()).start();
        }
    }
  static class Remoting implements Runnable  {
      public void run() {
          ApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/client2.xml");
         // ServiceOne so= (ServiceOne) context.getBean("serviceOne");
          ServiceOne so2= (ServiceOne) context.getBean("serviceTwo");
         // System.out.println(so.sayHello("King"));
         System.out.println(so2.sayHello("King"));
         System.out.println(so2.sayOk("K"));
      }
    }
}
