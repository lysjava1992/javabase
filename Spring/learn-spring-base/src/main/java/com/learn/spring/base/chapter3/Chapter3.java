package com.learn.spring.base.chapter3;

import com.learn.spring.base.chapter3.bean.DoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter3
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 20:16
 * @Version 1.0
 **/
public class Chapter3 {
    public static void main(String[] args) {
    ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter3.xml");
        DoService service1= (DoService) context.getBean("doService2");
//        System.out.println(service1.doWork1());
//        System.out.println(service1.doWork2());
        DoService service= (DoService) context.getBean("doService");
//        System.out.println(service.doWork1());
//        System.out.println(service.doWork2());


    }
}
