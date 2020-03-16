package com.hand.written.spring.formwork.beans;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *    事件监听
 * @ClassName BeanPostProcessor
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 11:11
 * @Version 1.0
 **/
public class BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean,String beanName){
        return bean;
    }
    public Object postProcessAfterInitialization(Object bean,String beanName){
        return bean;
    }
}
