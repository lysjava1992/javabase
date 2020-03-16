package com.hand.written.spring.formwork.beans;

import com.hand.written.spring.formwork.aop.AopConfig;
import com.hand.written.spring.formwork.aop.AopProxy;
import com.hand.written.spring.formwork.core.FactoryBean;

import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *          返回的包装类
 * @ClassName BeanWrapper
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 11:29
 * @Version 1.0
 **/
public class BeanWrapper extends FactoryBean{
  private AopProxy proxy=new AopProxy();
    private BeanPostProcessor postProcessor;
    private Object wrapperInstance;
    private Object originalInstance;
    public BeanWrapper(Object instance) {
        this.wrapperInstance = proxy.getProxy(instance);
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

     public void setAopConfig(AopConfig config){
         proxy.setConfig(config);
     }
}
