<<<<<<< HEAD
package com.learn.spring.base.chapter25;

import com.learn.spring.base.chapter25.dao.ActorDao;
import com.learn.spring.base.chapter25.entity.Actor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Chapter25App {
    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Chapter25Config.class);
        ActorDao actorDao=context.getBean(ActorDao.class);
        Actor actor= actorDao.getActor(1l);
       System.out.println(actor);
    }
}
=======
package com.learn.spring.base.chapter25;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter25App
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/4/3 11:00
 * @Version 1.0
 **/
public class Chapter25App {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context=new AnnotationConfigApplicationContext(Chapter25Config.class);
        StubEntitlementCalculationService entitlementCalculationService
                =  context.getBean(StubEntitlementCalculationService.class);

        // the profiling aspect is 'woven' around this method execution
        entitlementCalculationService.calculateEntitlement();
    }
}
>>>>>>> 9f719d1f3c36f64afdf53d32a9ce89827f8acba5
