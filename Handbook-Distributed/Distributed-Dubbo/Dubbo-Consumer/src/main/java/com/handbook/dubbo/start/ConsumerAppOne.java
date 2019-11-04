package com.handbook.dubbo.start;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.handbook.dubbo.api.ServiceOne;

/**
 * @description:
 *  调用端与服务提供端的依赖相同
 *  此案例 是点对点直连
 *
 *   dubbo 提供了版本控制
 * @author: Mr.Luan
 * @create: 2019-11-04 10:38
 **/
public class ConsumerAppOne {
    public static void main(String[] args) {
        //配置调用的服务
        ApplicationConfig application=new ApplicationConfig();
        //必须一致
        application.setName("client-one");



        //配置具体调用 点对点
        ReferenceConfig<ServiceOne> reference=new ReferenceConfig<ServiceOne>();
        reference.setApplication(application);
        reference.setUrl("dubbo://192.168.187.50:20880/com.handbook.dubbo.api.ServiceOne" );
        reference.setInterface(ServiceOne.class);
        // 版本号必须一致
        reference.setVersion("1.0.0");



        ReferenceConfig<ServiceOne> reference2=new ReferenceConfig<ServiceOne>();
        reference2.setApplication(application);
        reference2.setUrl("dubbo://192.168.187.50:20880/com.handbook.dubbo.api.ServiceOne" );
        reference2.setInterface(ServiceOne.class);
        // 版本号必须一致
        reference2.setVersion("2.0.0");

        ServiceOne serviceOne=reference.get();
        ServiceOne serviceOne2=reference2.get();
        System.out.println(serviceOne.sayHello("King"));
        System.out.println(serviceOne2.sayHello("King"));
    }
}
