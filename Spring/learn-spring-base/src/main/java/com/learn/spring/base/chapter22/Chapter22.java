package com.learn.spring.base.chapter22;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Chapter22 {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter22.xml");
        TestBean bean= (TestBean) context.getBean("testBean");
        System.out.println(bean.getValue());

        TestBeanService service=context.getBean(TestBeanService.class);
        service.testBean(bean);
    }
}
