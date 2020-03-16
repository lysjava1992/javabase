package com.hand.written.spring.formwork.core;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BeanFactory
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 9:58
 * @Version 1.0
 **/
public interface BeanFactory {
    /**
     *  根据bean的名字，获取在IOC容器中得到bean实例
     * @param name
     * @return
     */
    Object getBean(String name) ;
}
