package com.learn.spring.base.chapter27;

import com.learn.spring.base.chapter27.dao.ActorRepository;
import com.learn.spring.base.chapter27.entity.Actor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Chapter27App {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("spring_chapter27.xml");
        ActorRepository repository=context.getBean(ActorRepository.class);
        Actor actor=new Actor();
        actor.setFirstName("张");
        actor.setAge(21);
        actor.setLastName("三");
        repository.savaActor(actor);
        List<Actor> list=repository.getAll();
        for (Actor ac:list) {
            System.out.println(ac);
        }
    }
}
