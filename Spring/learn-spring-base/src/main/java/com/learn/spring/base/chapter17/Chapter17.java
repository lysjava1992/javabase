package com.learn.spring.base.chapter17;

import com.learn.spring.base.chapter17.service.HelloService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Chapter17 {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(SpringConfigurationRootClass.class);
        HelloService helloService1=context.getBean(HelloService.class);
        helloService1.sayHello();
        HelloService helloService2= (HelloService) context.getBean("helloService3");
        helloService2.sayHello();
        System.out.println(helloService2);
        Object[] objs = new Object[]{""};
       System.out.println(context.getMessage("Hi",objs, Locale.CHINESE));
       System.out.println(context.getMessage("Hi",objs, Locale.US));

    }
}
