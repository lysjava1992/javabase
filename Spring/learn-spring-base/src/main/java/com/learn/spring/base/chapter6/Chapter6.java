package com.learn.spring.base.chapter6;

import com.learn.spring.base.chapter6.service.BaseService2;
import com.learn.spring.base.chapter6.service.BaseService3;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter6
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 14:47
 * @Version 1.0
 **/
public class Chapter6 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter6.xml");
        BaseService2 service2= (BaseService2) context.getBean("baseService2");
             service2.add();
             service2.find();
        BaseService3 service3= (BaseService3) context.getBean("baseService3");
        service3.add();
        service3.find();
    }
}
