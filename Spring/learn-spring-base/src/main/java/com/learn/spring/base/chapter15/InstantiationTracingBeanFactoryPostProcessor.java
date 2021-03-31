package com.learn.spring.base.chapter15;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class InstantiationTracingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println(beanFactory.getBean("beanOne"));
        System.out.println(beanFactory.getBeanDefinition("beanTwo"));
        System.out.println("----------------");
    }
}
