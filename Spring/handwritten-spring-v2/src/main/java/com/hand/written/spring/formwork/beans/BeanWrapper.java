package com.hand.written.spring.formwork.beans;

import com.hand.written.spring.formwork.core.FactoryBean;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BeanWrapper
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 11:06
 * @Version 1.0
 **/
public class BeanWrapper extends FactoryBean {

    private BeanPostProcessor postProcessor;
    private Object wrapperInstance;
    private Object originalInstance;
    public BeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.originalInstance=instance;
    }

    public Object getWrapperInstance() {
        return wrapperInstance;
    }
    Class<?> getWrappedClass(){
        return this.wrapperInstance.getClass();
    }
    public BeanPostProcessor getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(BeanPostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

}
