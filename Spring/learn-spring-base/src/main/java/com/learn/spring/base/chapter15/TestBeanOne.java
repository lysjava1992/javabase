package com.learn.spring.base.chapter15;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class TestBeanOne implements InitializingBean , DisposableBean {
    public TestBeanOne() {
        System.out.println("-----[TestBeanOne]实例化-----");
    }

    public void  doSome(){
        System.out.println("-----[TestBeanOne]被调用-----");
    }
    public void afterPropertiesSet() throws Exception {
        System.out.println("-----[TestBeanOne]实例化注入完毕-----");
    }

    public void destroy() throws Exception {
      System.out.println("-----[TestBeanOne]即将销毁-----");
    }
}
