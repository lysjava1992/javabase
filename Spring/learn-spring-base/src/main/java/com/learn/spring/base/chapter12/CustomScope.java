package com.learn.spring.base.chapter12;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomScope implements Scope {
    static AtomicInteger count=new AtomicInteger(0);
    static final int total=2;
    static ConcurrentHashMap<String,Object> cache=new ConcurrentHashMap<String, Object>(64);
    /**
     *
     * @param name  Bean name
     * @param objectFactory Bean工厂
     * @return
     */
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object result=null;
        int temp=count.getAndIncrement();
        if(temp%total==0){
            result=objectFactory.getObject();
            cache.put(name,result);
        }
        if(null==result){
            result=cache.get(name);
        }
        return result;
    }

    public Object remove(String name) {
        return cache.remove(name);
    }

    public void registerDestructionCallback(String name, Runnable callback) {
      System.out.println(name+" is destroy");
    }

    public Object resolveContextualObject(String key) {
        return null;
    }

    public String getConversationId() {
        return null;
    }
}
