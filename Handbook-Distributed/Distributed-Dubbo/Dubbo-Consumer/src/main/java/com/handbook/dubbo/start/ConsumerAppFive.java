package com.handbook.dubbo.start;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.rpc.Protocol;
import com.handbook.dubbo.api.ServiceOne;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: Mr.Luan
 *  dubbo SPI
 *
 *   JAVA使用ServiceLoader来加载扩展类
 *   Dubbo使用ExtensionLoader来加载
 * @create: 2019-11-04 13:06
 **/
public class ConsumerAppFive {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:dubbo/client2.xml");
        ServiceOne so= (ServiceOne) context.getBean("serviceOne");
        System.out.println(so.sayHello("King"));


        Protocol protocol= ExtensionLoader
                             .getExtensionLoader(Protocol.class)
                            .getExtension("myProtocol");
        System.out.println(protocol.getDefaultPort());
    }

}
