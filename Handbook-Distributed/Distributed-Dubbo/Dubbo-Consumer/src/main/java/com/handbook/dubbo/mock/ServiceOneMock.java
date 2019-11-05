package com.handbook.dubbo.mock;

import com.handbook.dubbo.api.ServiceOne;

/**
 * @description: mock 当调用超时时 会调用本地方法返回
 * @author: Mr.Luan
 * @create: 2019-11-05 11:13
 **/
public class ServiceOneMock implements ServiceOne {
    public String sayHello(String name) {
        return "服务繁忙...........";
    }

    public String sayOk(String name) {
        return "服务繁忙...........";
    }
}
