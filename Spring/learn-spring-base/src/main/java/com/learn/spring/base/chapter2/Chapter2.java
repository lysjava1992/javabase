package com.learn.spring.base.chapter2;

import com.learn.spring.base.chapter2.bean.Toy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter2
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 19:55
 * @Version 1.0
 **/
public class Chapter2 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter2.xml");
        Toy toy1= (Toy) context.getBean("toy1");
        System.out.println(toy1);
        Toy toy2= (Toy) context.getBean("toy2");
        System.out.println(toy2);
    }
}
