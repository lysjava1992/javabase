package com.learn.spring.base.chapter16;

import org.springframework.beans.factory.FactoryBean;

public class BeanDemoFactory implements FactoryBean {
    public Object getObject() throws Exception {
        return new BeanDemo();
    }

    public Class<?> getObjectType() {
        return BeanDemo.class;
    }
}
