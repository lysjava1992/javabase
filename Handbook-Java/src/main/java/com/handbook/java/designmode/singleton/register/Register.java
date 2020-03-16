package com.handbook.java.designmode.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Register
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/11 19:49
 * @Version 1.0
 **/
public class Register {
    private Register(){}
    private static Map<String,Object> register=new ConcurrentHashMap<>();
    public static Object getInstance(String className){
        synchronized (register){
            if(className==null){
                return null;
            }else if(register.containsKey(className)){
                return register.get(className);
            }else {
                Object obj=null;
                try {
                    Class aClass=Class.forName(className);
                    if(aClass!=null){
                        obj=aClass.newInstance();
                        register.put(className,obj);
                        return obj;
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    }
}
