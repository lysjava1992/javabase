package com.handbook.dubbo.start;

import com.alibaba.dubbo.container.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @description:
 *   发布 基于xml配置文件
 *         此案例配置文件 server1.xml
 *   启动方式
 *
 *    支持的协议
 *    dubbo
 *     Dubbo缺省协议采用单一长连接和NIO异步通讯，
 *           适合于小数据量大并发的服务调用，以及服务消费者机器数远大于服务提供者机器数的情况。
 *          连接个数：单连接    连接方式：长连接   传输协议：TCP    传输方式：NIO异步传输   序列化：Hessian二进制序列化
 *          适用范围：传入传出参数数据包较小（建议小于100K），消费者比提供者个数多，单一消费者无法压满提供者，尽量不要用dubbo协议传输大文件或超大字符串。
 *         适用场景：常规远程服务方法调用
 *     为什么要消费者比提供者个数多：
 *          因dubbo协议采用单一长连接，假设网络为千兆网卡(1024Mbit=128MByte)，
 *          根据测试经验数据每条连接最多只能压满7MByte(不同的环境可能不一样，供参考)，理论上1个服务提供者需要20个服务消费者才能压满网卡。
 *     为什么不能传大包：
 *         因dubbo协议采用单一长连接，如果每次请求的数据包大小为500KByte，假设网络为千兆网卡(1024Mbit=128MByte)，每条连接最大7MByte(不同的环境可能不一样，供参考)，
 *         单个服务提供者的TPS(每秒处理事务数)最大为：128MByte / 500KByte = 262。单个消费者调用单个服务提供者的TPS(每秒处理事务数)最大为：7MByte / 500KByte = 14。
 *     为什么采用异步单一长连接：
 *       因为服务的现状大都是服务提供者少，通常只有几台机器，而服务的消费者多，可能整个网站都在访问该服务，
 *       比如Morgan的提供者只有6台提供者，却有上百台消费者，每天有1.5亿次调用，如果采用常规的hessian服务，服务提供者很容易就被压跨，通过单一连接，保证单一消费者不会压死提供者，
 *       长连接，减少连接握手验证等，并使用异步IO，复用线程池，防止C10K问题。
 *   RMI
 *    RMI协议采用JDK标准的java.rmi.*实现，采用阻塞式短连接和JDK标准序列化方式 Java标准的远程调用协议。
 *       连接个数：多连接  连接方式：短连接 传输协议：TCP 传输方式：同步传输 序列化：Java标准二进制序列化
 *       适用范围：传入传出参数数据包大小混合，消费者与提供者个数差不多，可传文件。
 *       适用场景：常规远程服务方法调用，与原生RMI服务互操作
 *  hessian
 *   Hessian协议用于集成Hessian的服务，Hessian底层采用Http通讯，采用Servlet暴露服务，Dubbo缺省内嵌Jetty作为服务器实现
 *    基于Hessian的远程调用协议。
 *   连接个数：多连接  连接方式：短连接 传输协议：HTTP 传输方式：同步传输  序列化：Hessian二进制序列化
 *   适用范围：传入传出参数数据包较大，提供者比消费者个数多，提供者压力较大，可传文件。
 *   适用场景：页面传输，文件传输，或与原生hessian服务互操作
 *  http
 *  采用Spring的HttpInvoker实现  基于http表单的远程调用协议。
 *   连接个数：多连接  连接方式：短连接 传输协议：HTTP 传输方式：同步传输 序列化：表单序列化（JSON）
 *   适用范围：传入传出参数数据包大小混合，提供者比消费者个数多，可用浏览器查看，可用表单或URL传入参数，暂不支持传文件。
 *   适用场景：需同时给应用程序和浏览器JS使用的服务。
 * webservice
 *  基于CXF的frontend-simple和transports-http实现
 *  连接个数：多连接 连接方式：短连接 传输协议：HTTP 传输方式：同步传输 序列化：SOAP文本序列化
 *  适用场景：系统集成，跨语言调用。
 * thrif
 *  Thrift是Facebook捐给Apache的一个RPC框架，当前 dubbo 支持的 thrift 协议是对 thrift 原生协议的扩展，
 *  在原生协议的基础上添加了一些额外的头信息，比如service name，magic number等。
 *
 * @author: Mr.Luan
 * @create: 2019-11-04 11:28
 **/
public class ProviderAppTwo {
    public static void main(String[] args) throws IOException {
            startOne();
          //  startTwo(args);
    }

    /**
     * 启动方式 ：利用dubbo给的启动方法
     *  1）会自动扫描配置文件resources/META-INF/spring/dubbo-*.xml
     *  2）也可以指定自己的配置文件 resources/dubbo.properties
     *          dubbo.spring.config=classpath:dubbo/*.xml（自己的配置路径）
     */
    private static void startTwo(String[] args) {
        Main.main(args);
    }

    /**
     * 启动方式 指定配置文件 Spring IOC容器启动
     */
    private static void startOne() throws IOException {
        ClassPathXmlApplicationContext context=new
                 ClassPathXmlApplicationContext("classpath:dubbo/server1.xml");
        context.start();
        System.out.println("---------服务已发布---------");
        System.in.read();

    }
}
