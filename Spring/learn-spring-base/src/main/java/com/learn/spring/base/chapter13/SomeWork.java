package com.learn.spring.base.chapter13;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.Lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SomeWork implements InitializingBean , Lifecycle, BeanNameAware {
    private String name;
    public SomeWork() {
        //第一步
        System.out.println("实例化");
    }
    public void setName(String name) {
        // 第二部
        System.out.println("注入");
        this.name = name;
    }
    public void setBeanName(String name) {
        System.out.println("设置bean名称"+name);
    }

    public String getName() {
        return name;
    }




    @PostConstruct
    public void  init(){
        System.out.println("----@PostConstruct初始化方法init----");
    }

    public void  init2(){
        System.out.println("----XML配置初始化方法init2----");
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

            System.out.println("----afterPropertiesSet----");

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


}
