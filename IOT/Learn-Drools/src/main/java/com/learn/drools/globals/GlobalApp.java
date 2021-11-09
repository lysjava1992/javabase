package com.learn.drools.globals;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;

public class GlobalApp {
    public static void main(String[] args) {
        KieServices services=KieServices.Factory.get();
        KieContainer container=services.getKieClasspathContainer();
        KieSession session=container.newKieSession("rule-global");
        // 必须设置初始值
        // 在drl文件中只能声明，不能赋值
        session.setGlobal("count",12);
        session.setGlobal("list",new ArrayList<String>());
        DoService doService=new DoService();
        session.setGlobal("doService",doService);
        session.fireAllRules();
        // count 并不会改变
        System.out.println("全局变量[count] :"+session.getGlobal("count"));

        // 集合 Map java 等引用类型
        // 值可以改变
        // 地址不变
        ArrayList<String> names= (ArrayList<String>) session.getGlobal("list");
        names.forEach(System.out::println);
        System.out.println("doService 调用次数: "+doService.getCount());
        session.dispose();
    }
}
