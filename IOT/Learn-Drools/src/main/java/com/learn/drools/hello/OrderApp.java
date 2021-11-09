package com.learn.drools.hello;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import java.util.Arrays;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName OrderApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/11/8 21:30
 * @Version 1.0
 **/
public class OrderApp {
    public static void main(String[] args) throws InterruptedException {
            KieServices kieServices = KieServices.Factory.get();
            //获取Kie容器
            //使用默认的自动加载 META-INF/kmodule.xml
            //从kieServices中获得容器实例，并加载规则文件
            KieContainer kieContainer = kieServices.newKieClasspathContainer();
            System.out.println("默认KieBase名称 " + kieContainer.getKieBaseNames().toArray()[0]);
            //获取会话
            KieSession session = kieContainer.newKieSession("all-rule");
            Order order = new Order(60d);
            order.setName("vip_1002");
            List<String> list= Arrays.asList("巧克力","牛奶");
            order.setGoods(list);
            //插入到规则内存中
            session.insert(order);
            //激活规则
            int count = session.fireAllRules();
            System.out.println(order.getInitialPrice()+"激活规则数量：" + count);
            System.out.println("实际收款: " + order.getPricePaid());
            //关闭会话
            session.dispose();
    }
}
