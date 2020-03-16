package com.hand.written.spring.formwork.aop;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName AopConfig            对application中的expression的封装
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/10 16:16
 * @Version 1.0
 **/
public class AopConfig {

    private Map<Method,Aspect> points=new HashMap<>();

    public void put(Method target,Object aspect,Method[]methods){
        this.points.put(target,new Aspect(aspect,methods));
    }
    public Aspect get(Method method){
        return this.points.get(method);
    }

    public boolean contains(Method method){
        return this.points.containsKey(method);
    }
    public class Aspect{
        private Object aspect;
        private Method[] points;
     public Aspect(Object aspect, Method[] points) {
         this.aspect = aspect;
         this.points = points;
     }

     public Object getAspect() {
         return aspect;
     }

     public void setAspect(Object aspect) {
         this.aspect = aspect;
     }

     public Method[] getPoints() {
         return points;
     }

     public void setPoints(Method[] points) {
         this.points = points;
     }

 }







}
