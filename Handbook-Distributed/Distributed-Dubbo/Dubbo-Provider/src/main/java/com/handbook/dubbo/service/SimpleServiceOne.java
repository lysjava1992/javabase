package com.handbook.dubbo.service;

import com.handbook.dubbo.api.ServiceOne;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-11-04 09:39
 **/
public class SimpleServiceOne  implements ServiceOne {
    public String sayHello(String name)
    {
        System.out.println("调用 执行---------");
        return "SimpleServiceOne: Hello "+name;
    }
}
