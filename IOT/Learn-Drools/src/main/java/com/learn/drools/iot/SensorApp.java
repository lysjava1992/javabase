package com.learn.drools.iot;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class SensorApp {
    public static void main(String[] args) {
        // 获取
        KieServices kieServices = KieServices.Factory.get();
        // 获取容器
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        // 获取会话
        KieSession session = kieContainer.newKieSession("all-rule");

        Sensor sensor = new Sensor();
        sensor.setName("温度传感器01");
        sensor.setType("温度传感器");
        sensor.setStatus("初始状态");
        sensor.setValue(150.8f);
        System.out.println(sensor);
        session.insert(sensor);
        session.fireAllRules();


        System.out.println(sensor);
        session.dispose();
    }
}
