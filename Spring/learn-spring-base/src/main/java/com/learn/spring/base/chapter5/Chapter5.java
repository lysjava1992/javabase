package com.learn.spring.base.chapter5;

import com.learn.spring.base.chapter5.service.BaseService1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter5
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 14:06
 * @Version 1.0
 **/
public class Chapter5 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring_chapter5.xml");
        BaseService1 baseService1= (BaseService1) context.getBean("baseService1");
        baseService1.doMethod1();
        baseService1.doMethod1();

        System.out.println("================");
        //代理类
        BaseService1 baseService1_2= (BaseService1) context.getBean("personProxy");
        baseService1_2.doMethod1();
        baseService1_2.doMethod2();
    }

}
