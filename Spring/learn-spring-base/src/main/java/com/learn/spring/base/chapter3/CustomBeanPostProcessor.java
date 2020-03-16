package com.learn.spring.base.chapter3;

import com.learn.spring.base.chapter3.bean.DoServiceImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Bean后置处理器 增强 扩展bean
 *
 * @ClassName CustomBeanPostProcessor
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/3/14 20:19
 * @Version 1.0
 **/
public class CustomBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
           System.out.println("准备初始化.......");
        return bean;
    }

    /**
     *  注册代理对象 实现增强扩展对象
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(final Object bean, String beanName) throws BeansException {

           //筛选过滤
         Class clazz=bean.getClass();
        if(clazz== DoServiceImpl.class){
            /**
             * JDK动态代理
             * 返回代理类的一个实例，返回后的代理类可以当作被代理类使用(可使用被代理类的在Subject接口中声明过的方法)。
             * 是实现java对象的动态代理的方法，他的三个参数
             *         loader ：真实对象的类加载器
             *         interfaces ：真实对象实现的所有接口，
             *        InvocationHandler ： 接口，传递一个匿名的内部类对象
             *
             */
            Object proxy= Proxy.newProxyInstance(clazz.getClassLoader(),
                                                 clazz.getInterfaces(),
               new InvocationHandler() {
                   /**
                    * 在此处进行对结果的增强
                    * @param proxy  代理监控对象
                    * @param method  doSome
                    * @param args    doSome参数
                    * @return
                    * @throws Throwable
                    * invoke 反射执行获取原始结果进行增强
                    */
                   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                      String result= (String) method.invoke(bean,args);
                    return result+" 增强【"+System.currentTimeMillis()+"】";
                }
            });

              return proxy;
        }
        return bean;
    }
}
