package com.learn.spring.boot.starter;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-25 17:07
 **/
public class HelloService {
    HelloProperties helloProperties;

    public HelloProperties getHelloProperties() {
        return helloProperties;
    }

    public void setHelloProperties(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name){
        return helloProperties.getPrefix()+"! 【"+name+"】"+helloProperties.getSuffix();
    }
}
