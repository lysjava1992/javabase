package com.hand.written.spring.formwork.beans;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BeanDefinition
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 10:05
 * @Version 1.0
 **/
public class BeanDefinition {
    private String className;
    private boolean lazyInit=false;
    private String factoryBeanName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
