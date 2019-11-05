package com.handbook.dubbo.api;

/**
 * @description:
 * 接口层 提供端和消费端都需要
 * 需要安装在本地仓库
 * @author: Mr.Luan
 * @create: 2019-11-04 09:37
 **/
public interface ServiceOne {
    String sayHello(String name);
    String sayOk(String name);
}
