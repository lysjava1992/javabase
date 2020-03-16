package com.learn.spring.base.chapter4;

import com.learn.spring.base.chapter4.bean.Consumer;
import com.learn.spring.base.chapter4.service.LoginService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter4
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/15 11:06
 * @Version 1.0
 **/
public class Chapter4 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter4.xml");
        Consumer consumer=new Consumer();
        consumer.setName("张三");
        consumer.setPassword("123456");
        LoginService service= (LoginService) context.getBean("loginService");
        service.login(consumer);
    }
}
