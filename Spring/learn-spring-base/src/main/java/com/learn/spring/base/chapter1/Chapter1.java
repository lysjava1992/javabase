package com.learn.spring.base.chapter1;

import com.learn.spring.base.chapter1.bean.Person;
import com.learn.spring.base.chapter1.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter1
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 19:12
 * @Version 1.0
 **/
public class Chapter1 {
    public static void main(String[] args) {
        //容器初始化
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter1.xml");
        Person person1= (Person) context.getBean("person1");
        System.out.println(person1.getCours().get(0));
        Person person2= (Person) context.getBean("person1");
        System.out.println(person2.getReportCard().get("数学"));
        //默认单例模式  True
        System.out.println(person1==person2);

        Person person3= (Person) context.getBean("person2");
        Person person4= (Person) context.getBean("person2");
        //false
        System.out.println(person3==person4);

        DemoService service= (DemoService) context.getBean("demoService");
        service.doSomething();
    }
}
