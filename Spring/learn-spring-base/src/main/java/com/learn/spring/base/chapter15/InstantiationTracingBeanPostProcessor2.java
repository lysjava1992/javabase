package com.learn.spring.base.chapter15;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

public class InstantiationTracingBeanPostProcessor2 implements BeanPostProcessor, Ordered {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----------前置处理器2----------");
        System.out.println(beanName+"["+bean+"]");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("----------后置处理器2----------");
        System.out.println(beanName+"["+bean+"]");
        return bean;
    }

    public int getOrder() {
        return 100;
    }
}
