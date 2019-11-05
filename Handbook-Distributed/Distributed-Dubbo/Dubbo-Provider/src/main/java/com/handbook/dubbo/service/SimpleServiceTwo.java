package com.handbook.dubbo.service;

import com.handbook.dubbo.api.ServiceOne;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-04 09:39
 **/
public class SimpleServiceTwo implements ServiceOne {
    public String sayHello(String name) {
        System.out.println("调用 执行---------V_2.0.0");
        return "SimpleServiceOne: Hello 【v_2.0.0】"+name;
    }

    public String sayOk(String name) {
        System.out.println("调用 sayOk---------V_2.0.0");
        return "SimpleServiceOne: OK 【v_2.0.0】"+name;
    }
}
