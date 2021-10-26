
package com.learn.spring.base.chapter25;

import com.learn.spring.base.chapter25.StubEntitlementCalculationService;
import com.learn.spring.base.chapter25.dao.ActorDao;
import com.learn.spring.base.chapter25.entity.Actor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Chapter25App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Chapter25Config.class);
        ActorDao actorDao=context.getBean(ActorDao.class);
        Actor actor= actorDao.getActor(1L);
       System.out.println(actor);
    }
}

