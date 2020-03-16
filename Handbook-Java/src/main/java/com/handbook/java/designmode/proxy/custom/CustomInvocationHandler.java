package com.handbook.java.designmode.proxy.custom;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomInvocationHandler
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 16:19
 * @Version 1.0
 **/
public interface CustomInvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
