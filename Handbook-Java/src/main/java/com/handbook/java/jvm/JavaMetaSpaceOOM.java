package com.handbook.java.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 *  方法区溢出
 *  配置：
 *   -XX:MaxMetaspaceSize=10m
 * @ClassName JavaMethodAreaOOM
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 19:34
 * @Version 1.0
 **/
public class JavaMetaSpaceOOM {
    static class OOMObject{

    }

    /**
     * java.lang.OutOfMemoryError: Metaspace
     * @param args
     */
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer=new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method,
                                        Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }
}
