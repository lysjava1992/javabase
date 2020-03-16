package com.hand.written.spring.formwork.context;

import com.hand.written.spring.formwork.beans.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName DefaultListableBeanFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 11:51
 * @Version 1.0
 **/
public class DefaultListableBeanFactory extends AbstractApplicationContext{
    /**
     * bean信息存储器
     */
    protected Map<String,BeanDefinition> beanDefinitionMap=new ConcurrentHashMap<>();

    @Override
    protected void onRefresh() {
        super.onRefresh();
    }

    @Override
    protected void refreshBeanFactory() {

    }
}
