package com.learn.spring.base.chapter11;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * ApplicationContextAware
 * 获取容器
 *
 */
public class PartsManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    public Parts createParts(){
        return this.applicationContext.getBean("parts",Parts.class);
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
                this.applicationContext=applicationContext;
    }
}
