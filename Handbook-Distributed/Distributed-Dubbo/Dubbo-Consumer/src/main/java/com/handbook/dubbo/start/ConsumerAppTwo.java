package com.handbook.dubbo.start;

import com.handbook.dubbo.api.ServiceOne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: Mr.Luan
 *  基于Spring 使用配置文件 从IOC容器中取值
 *
 *
 *  此案例对应配置文件 client1.xml    直连
 *  支持版本控制
 * @create: 2019-11-04 13:06
 **/
public class ConsumerAppTwo {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/client1.xml");
        ServiceOne so= (ServiceOne) context.getBean("serviceOne");

        ServiceOne so2= (ServiceOne) context.getBean("serviceTwo");

        System.out.println(so.sayHello("King"));
        System.out.println(so2.sayHello("King"));
    }
}
