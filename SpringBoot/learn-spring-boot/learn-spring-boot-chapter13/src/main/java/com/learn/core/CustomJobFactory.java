package com.learn.core;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomJobFactory extends AdaptableJobFactory implements ApplicationContextAware {
    private static ApplicationContext ctx=null;
    protected  Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        AutowireCapableBeanFactory capableBeanFactory = ctx.getAutowireCapableBeanFactory();
        Object jobInstance = super.createJobInstance(bundle);
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
    }
}
