package com.learn.spring.base.chapter13;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SomeWork implements InitializingBean , Lifecycle, BeanNameAware {
    private String name;
    public SomeWork() {
        System.out.println("实例化");
    }
    public void setName(String name) {
        System.out.println("注入");
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void  init(){
        System.out.println("----初始化方法一----");
    }
    @PostConstruct
    public void  init2(){
        System.out.println("----初始化方法二----");
    }
    public void  init3(){
        System.out.println("----初始化方法三----");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }
    /**
     * InitializingBean  接口
     * @throws Exception
     */
    public void afterPropertiesSet() throws Exception {
           init();
    }

    public void start() {
        System.out.println("----start---");
    }

    public void stop() {
        System.out.println("----stop---");
    }

    public boolean isRunning() {
        System.out.println("----running---");
        return false;
    }

    public void setBeanName(String name) {
        System.out.println("=========="+name);
    }
}
