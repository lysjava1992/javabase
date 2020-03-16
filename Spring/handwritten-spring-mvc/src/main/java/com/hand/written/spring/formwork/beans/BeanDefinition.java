package com.hand.written.spring.formwork.beans;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BeanDefinition
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 10:36
 * @Version 1.0
 **/
public class BeanDefinition {
    /**
     * 全限定名
     */
    private String cassName;
    /**
     * 是否懒加载
     */
    private boolean lazyInit=false;
    /**
     * 简化名
     */
    private String factoryBeanName;

    public String getCassName() {
        return cassName;
    }

    public void setCassName(String cassName) {
        this.cassName = cassName;
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

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "cassName='" + cassName + '\'' +
                ", lazyInit=" + lazyInit +
                ", factoryBeanName='" + factoryBeanName + '\'' +
                '}';
    }
}
