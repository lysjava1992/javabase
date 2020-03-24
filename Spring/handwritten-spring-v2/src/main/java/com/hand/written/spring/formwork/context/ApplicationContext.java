package com.hand.written.spring.formwork.context;

import com.hand.written.demo.action.DemoAction;
import com.hand.written.spring.formwork.annotation.Autowired;
import com.hand.written.spring.formwork.annotation.Controller;
import com.hand.written.spring.formwork.annotation.Service;
import com.hand.written.spring.formwork.beans.BeanDefinition;
import com.hand.written.spring.formwork.beans.BeanPostProcessor;
import com.hand.written.spring.formwork.beans.BeanWrapper;
import com.hand.written.spring.formwork.context.support.BeanDefinitionReader;
import com.hand.written.spring.formwork.core.BeanFactory;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 * <p>
 * 相当于 ClassPathXmlApplicationContext
 *
 * @ClassName ApplicationContext
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 9:56
 * @Version 1.0
 **/
public class ApplicationContext implements BeanFactory {
    private String[] configLocation;

    private BeanDefinitionReader reader;
    /**
     * 配置信息
     */
    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    /**
     * 单例容器
     */
    private Map<String, Object> beanCacheMap = new HashMap<>();
    /**
     * 被代理的对象
     */
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... configLocation) {
        this.configLocation = configLocation;
        refresh();
    }

    public void refresh() {

        //定位
        this.reader = new BeanDefinitionReader(configLocation);
        //加载
        List<String> beanDefinitions = reader.loadBeadDefinitions();
        //注册
        doRegistry(beanDefinitions);
        //依赖注入
        doAuthorised();

        DemoAction action= (DemoAction) this.getBean("demoAction");
        action.query(null,null,"King");

    }

    private void doAuthorised() {
        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry:this.beanDefinitionMap.entrySet()){
            String beanName=beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                getBean(beanName);
            }
        }

    }

    public void populateBean(String beanName,Object instance){
        Class clazz=instance.getClass();
        if(clazz.isAnnotationPresent(Controller.class)||clazz.isAnnotationPresent(Service.class)){
            Field[] fields=clazz.getDeclaredFields();
            for (Field field:fields){
                if(!field.isAnnotationPresent(Autowired.class)){continue;}
                Autowired autowired=field.getAnnotation(Autowired.class);
                String autowiredBeanName=autowired.value().trim();
                if("".equals(autowiredBeanName)){
                    autowiredBeanName=field.getType().getName();
                }

                if(this.beanWrapperMap.get(autowiredBeanName)==null){
                     getBean(autowiredBeanName);
                }
                field.setAccessible(true);
                try {
                    field.set(instance,this.beanWrapperMap.get(autowiredBeanName).getWrapperInstance());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 将beanDefinition注册
     *
     * @param beanDefinitions
     */
    private void doRegistry(List<String> beanDefinitions) {
        try {
            for (String className : beanDefinitions) {
                Class<?> beanClass = Class.forName(className);
                //接口
                if (beanClass.isInterface()) {
                    continue;
                }
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
                }
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    //多个类 覆盖
                    this.beanDefinitionMap.put(i.getName(), beanDefinition);
                }
                //beanName
                //1.默认首字母小写

                //2.自定义

                //3.接口注入
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取BeanDefinition信息
     * 反射创建实例返回
     * <p>
     * Spring不会返回最原始对象 会返回一个包装类 beanWrapper
     * 保留了oop关系
     * 为扩展和AOP做基础
     *
     * @param beanName
     * @return
     */
    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        try {
            //通知事件
            BeanPostProcessor beanPostProcessor=new BeanPostProcessor();

            Object instance = instantiationBean(beanDefinition);
            if (null == instance) {
                return null;
            }
            beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
            BeanWrapper beanWrapper = new BeanWrapper(instance);
            this.beanWrapperMap.put(beanName,beanWrapper);
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            populateBean(beanName,instance);
            return this.beanWrapperMap.get(beanName).getWrapperInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Object instantiationBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getClassName();
        try {
            if (this.beanCacheMap.containsKey(className)) {
                return this.beanCacheMap.get(className);
            } else {
                Class<?> calzz = Class.forName(className);
                instance = calzz.newInstance();
                this.beanCacheMap.put(className, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }
}
