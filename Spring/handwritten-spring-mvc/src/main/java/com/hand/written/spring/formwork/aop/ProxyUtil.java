package com.hand.written.spring.formwork.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ProxyUtil
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 17:13
 * @Version 1.0
 * 从代理类获取原生类
 **/
public class ProxyUtil {
    public static Object getTargetObject(Object proxy)throws Exception{
          if(!isAopProxy(proxy)){return  proxy;}
          return getProxyTargetObject(proxy);
    }
    private static boolean isAopProxy(Object object){
        return Proxy.isProxyClass(object.getClass());
    }
    private   static Object getProxyTargetObject(Object proxy) throws Exception{
        Field h=proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        AopProxy aopProxy= (AopProxy) h.get(proxy);
        Field target=aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);

        return target.get(aopProxy);
    }
}
