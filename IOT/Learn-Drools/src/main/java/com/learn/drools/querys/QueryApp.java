package com.learn.drools.querys;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

public class QueryApp {
    public static void main(String[] args) {
        KieServices kieServices=KieServices.Factory.get();
        KieContainer container=kieServices.getKieClasspathContainer();
        KieSession session=container.newKieSession("rule-query");

        for (int i = 0; i <10 ; i++) {
             Device device=new Device((30F*i),"设备_"+i);
             session.insert(device);
        }
      //  executeQuery(session,"normalDevice");
        //executeQuery(session,"byName","设备_1");
        session.fireAllRules();
        executeQuery(session,"normalDevice");
        executeQuery(session,"byName","设备_2");
        session.dispose();
    }


    static void  executeQuery(KieSession session,String query, Object... arguments){
         System.out.println("执行Query--"+query);
        QueryResults results=session.getQueryResults(query,arguments);
        System.out.println("符合条件的Fact对象个数" + results.size());
        for (QueryResultsRow row : results)
        {
            Device s = (Device) row.get("$device");
            System.out.println(s);
        }

    }
}
