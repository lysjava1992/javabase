package com.learn.drools.attribute;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class AttributeTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("drools.dateformat","yyyy-MM-dd hh:mm:ss" );
        KieServices kieServices=KieServices.Factory.get();
        KieContainer container=kieServices.getKieClasspathContainer();
        KieSession session=container.newKieSession("rule-attr");
        session.insert(new Count());
        // 触发特定议程组
      //   session.getAgenda().getAgendaGroup("agent_1").setFocus();
        session.fireAllRules();
        // 用于触发定制规则
         session.fireUntilHalt();

        Thread.sleep(10000);
        session.halt();
        session.dispose();
    }
}
