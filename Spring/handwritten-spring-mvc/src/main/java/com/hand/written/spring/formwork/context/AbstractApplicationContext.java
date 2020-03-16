package com.hand.written.spring.formwork.context;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AbstractApplicationContext
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 11:45
 * @Version 1.0
 **/
public abstract class AbstractApplicationContext {
    /**
     * 提供给子类重写
     */
    protected void onRefresh() {

    }
    protected abstract void refreshBeanFactory();
}
