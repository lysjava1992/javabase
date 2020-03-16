package com.learn.spring.base.chapter7;

import com.learn.spring.base.chapter7.service.BaseService4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter7
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 15:44
 * @Version 1.0
 **/
public class Chapter7 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter7.xml");
        BaseService4 service= (BaseService4) context.getBean("baseService4");
        service.add();
        System.out.println("=====================");
        service.find();
        System.out.println("=====================");
        service.delete();
    }
}
