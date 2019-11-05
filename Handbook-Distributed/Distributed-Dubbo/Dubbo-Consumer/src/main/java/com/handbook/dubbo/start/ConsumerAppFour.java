package com.handbook.dubbo.start;

import com.handbook.dubbo.api.ServiceOne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: Mr.Luan
 *    容错机制： 容错机制指的是某种系统控制在一定范围内的一种允许或包容犯错情况的发生，
 *         即：在消费端发起调用后，若提供端崩溃或异常，网络等原因导致调用失败时采取的策略
 *         在分布式架构下，网络、硬件、应用都可能发生故障，由于各个服务之间可能存在依赖关系，
 *         如果一条链路中的其中一个节点出现故障，将会导致**雪崩效应**。为了减少某一个节点故障的影响范围，
 *         dubbo提供了6种容错机制
 *             - failover             默认  重试其他服务器 2次 一共3次
 *             - failsafe             失败安全 ,记录日志
 *             - failfast             快速失败 立马报错
 *             - failback             失败自动回复，记录失败请求，重发
 *             - forking forks        设置并发数 同时调用多个 有一个返回即成功  耗资源
 *             - broadcast            广播 任意一台报错，则报错
 *
 *     服务降级：
 *         多个服务之间可能由于服务没有启动或者网络不通，调用中会出现远程调用失败;
 *         流量高峰服务请求过大，需要停止部分服务以保证核心业务的正常运行；
 *
 *     mock
 *        允许在远程调用过程中因 网络延迟（超时） 提供者服务宕机等原因使
 *        结果不能正常返回时可以返回一个临时结果
 *        前提：注册中心中有正确的地址/或消费端有缓存
 *        假设服务提供端根本没启动，注册中心中没有响应的服务地址 是不会触发mock的
 *
 *    此案例对应配置文件 client3.xml     client4.xml
 *   支持版本控制
 * @create: 2019-11-04 13:06
 **/
public class ConsumerAppFour {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/client4.xml");
         for(int i=0;i<30;i++){
             System.out.print("发起调用-------------"+i);
             ServiceOne so= (ServiceOne) context.getBean("serviceOne");
             System.out.println(so.sayHello("King"));
             Thread.sleep(3000);
         }

    }
}
