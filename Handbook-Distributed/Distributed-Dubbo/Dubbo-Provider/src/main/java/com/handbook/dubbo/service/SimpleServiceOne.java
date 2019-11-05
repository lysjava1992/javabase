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
        System.out.println("调用 执行---------sayHello");
        //  int a=1/0;
        // 抛出的异常 调用端也会抛出
        return "SimpleServiceOne: Hello "+name;
    }
    public String sayOk(String name)
    {
        System.out.println("调用 执行---------sayOk");
        return "SimpleServiceOne: Ok "+name;
    }
}
