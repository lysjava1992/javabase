package com.handbook.dubbo.start;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.handbook.dubbo.api.ServiceOne;
import com.handbook.dubbo.service.SimpleServiceOne;
import com.handbook.dubbo.service.SimpleServiceTwo;

import java.io.IOException;

/**
 * @description:
 *  基本
 *   doubbo 是基于spring来管理的
 *      <dependency>
 *          <groupId>com.alibaba</groupId>
 *          <artifactId>dubbo</artifactId>
 *           <version>2.5.3</version>
 *      </dependency>
 *
 *        <version>2.6.6</version> 版本依赖于netty 需要netty的依赖包
 *
 *  已经引入spring
 *  Dubbo是一款高性能 轻量级的开源java RPC框架，
 *  它提供了三大核心能力：
 *    1）面向接口的远程方法调用，
 *    2）只能容错和负载均衡，
 *    3）以及服务自动注册和发现（dubbo官方推荐使用Zookeeper注册中心）
 *
 *  本案例： 直连
 * @author: Mr.Luan
 * @create: 2019-11-04 09:49
 **/
public class ProviderAppOne {

    public static void main(String[] args) throws IOException {
        //需要发布的服务
        ServiceOne so=new SimpleServiceOne();
        ServiceOne so2=new SimpleServiceTwo();
        //服务发布信息   名称等信息设置 ApplicationConfig
        ApplicationConfig application=new ApplicationConfig();
        application.setName("server-one");

        //注册仓库信息   N/A 无注册中心点对点直连
        RegistryConfig registry=new RegistryConfig();
        registry.setAddress("N/A");

         //协议信息  dubbo协议
        ProtocolConfig protocol=new ProtocolConfig();
        protocol.setName("dubbo");
        protocol.setThreads(100);

        //服务发布
        ServiceConfig<ServiceOne> service=new ServiceConfig<ServiceOne>();
        service.setApplication(application);
        service.setRegistry(registry);
        service.setProtocol(protocol);
        service.setInterface(ServiceOne.class);
        service.setRef(so);
        service.setVersion("1.0.0");
        service.export();
        ServiceConfig<ServiceOne> service2=new ServiceConfig<ServiceOne>();
        service2.setApplication(application);
        service2.setRegistry(registry);
        service2.setProtocol(protocol);
        service2.setInterface(ServiceOne.class);
        service2.setRef(so2);
        service2.setVersion("2.0.0");
        service2.export();
        System.in.read();
        System.out.print("-----------服务发布-----------");
    }
}






