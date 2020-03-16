package com.hand.written.spring.formwork.context;

import com.hand.written.demo.controller.TestController;
import com.hand.written.spring.formwork.annotation.Autowired;
import com.hand.written.spring.formwork.annotation.Controller;
import com.hand.written.spring.formwork.annotation.Service;
import com.hand.written.spring.formwork.aop.AopConfig;
import com.hand.written.spring.formwork.beans.BeanDefinition;
import com.hand.written.spring.formwork.beans.BeanPostProcessor;
import com.hand.written.spring.formwork.beans.BeanWrapper;
import com.hand.written.spring.formwork.context.support.BeanDefinitionReader;
import com.hand.written.spring.formwork.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ApplicationContext
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:34
 * @Version 1.0
 **/
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    AopConfig config=new AopConfig();
    /**
     * 配置文件
     */
    private String[] configLocations;
    /**
     * 配置文件解析器
     */
    private BeanDefinitionReader reader;
  /**
     * 单例存储
     */
    private Map<String,Object>beanCacheMap=new HashMap<>();
    /**
     * 真正的返回 包装实例
     */
    private Map<String ,BeanWrapper>beanWrapperMap=new ConcurrentHashMap<>();
    public ApplicationContext(String...locations) {
        this.configLocations=locations;
        refresh();
    }

    /**
     * 初始化容器
     */
    private void refresh() {

        //定位
     this.reader=new BeanDefinitionReader(configLocations);
        //加载
     List<String> packageClassList=reader.getBeanClassList();
        //注册
     doRegistry(packageClassList);
        //依赖注入
     doAuthorised();

//        TestController testController= (TestController) getBean("testController");
//        testController.test(null,null,"HHHH");
    }

    private void doAuthorised() {
        for (Map.Entry<String,BeanDefinition> beanDefinitionEntry:this.beanDefinitionMap.entrySet()){
            if(!beanDefinitionEntry.getValue().isLazyInit()){
                //非懒加载
                getBean(beanDefinitionEntry.getKey());

            }
        }
    }

    @Override
    public Object getBean(String beanName) {

        BeanDefinition beanDefinition=this.beanDefinitionMap.get(beanName);
        try {
            //通知事件
            BeanPostProcessor beanPostProcessor=new BeanPostProcessor();

             Object instance=instantiationBean(beanDefinition);
             if(instance==null){return null;}

             //前置事件
             beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

             BeanWrapper beanWrapper=new BeanWrapper(instance);
             beanWrapper.setPostProcessor(beanPostProcessor);
             beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
             this.beanWrapperMap.put(beanName,beanWrapper);
            //后置事件
            beanPostProcessor.postProcessAfterInitialization(instance,beanName);
             populateBean(beanName,instance);
             return this.beanWrapperMap.get(beanName).getWrapperInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private AopConfig instantionAopConfig(BeanDefinition beanDefinition) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {


       String expression=reader.getConfig().getProperty("point.cut");
       String[] before=reader.getConfig().getProperty("aspect.before").split("\\s");
       String[] after=reader.getConfig().getProperty("aspect.after").split("\\s");

       String className=beanDefinition.getCassName();
       Class<?>clazz=Class.forName(className);
       Pattern pattern=  Pattern.compile(expression);

       Class aspectClass=Class.forName(before[0]);
       for (Method m:clazz.getMethods()){

           Matcher matcher=pattern.matcher(m.toString());
          if(matcher.matches()){
              config.put(m,aspectClass.newInstance(),new Method[]{aspectClass.getMethod(before[1]),aspectClass.getMethod(after[1])});
          }
       }
        return config;
    }
    /**
     *  注入
     * @param beanName
     * @param instance
     */
    private void populateBean(String beanName, Object instance) {

        Class clazz=instance.getClass();
        //有@Controller或者@Service注解 要扫描字段注入
        if(clazz.isAnnotationPresent(Controller.class)||clazz.isAnnotationPresent(Service.class)){
            Field[] fields=clazz.getDeclaredFields();
            for (Field field:fields){

                if(field.isAnnotationPresent(Autowired.class)){
                    Autowired autowired=field.getAnnotation(Autowired.class);
                    String autowiredName=autowired.value().trim();
                    if("".equals(autowiredName)){
                        autowiredName=field.getType().getName();
                    }
                    //判断要注入的bean是否实例化否则递归注入
                   if(this.beanWrapperMap.get(autowiredName)==null){
                        getBean(autowiredName);
                   }
                   //注入
                    field.setAccessible(true);
                    try {
                        field.set(instance,this.beanWrapperMap.get(autowiredName).getWrapperInstance());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 真正实例化一个对象
     * @param beanDefinition
     * @return
     */
    private Object instantiationBean(BeanDefinition beanDefinition) {
        Object instance=null;
        String className=beanDefinition.getCassName();

        try {
            if(this.beanCacheMap.containsKey(className)){
                return this.beanCacheMap.get(className);
            }else {
                Class<?> clazz=Class.forName(className);
                instance=clazz.newInstance();
                this.beanCacheMap.put(className,instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance;
    }

    private void doRegistry(List<String> packageClassList) {
        try {
            for (String className:packageClassList){
                Class<?> clazz=Class.forName(className);
                //是否为接口
                if(clazz.isInterface()){continue;}
                BeanDefinition beanDefinition=reader.registerBean(className);
                if(beanDefinition!=null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }
                //获取实现的所有接口
                Class<?>[] interfaces=clazz.getInterfaces();
              for (Class<?> i:interfaces){
                  this.beanDefinitionMap.put(i.getName(),beanDefinition);
              }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String[] getBeanDefinitionNames() {

        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }


    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }
    public Properties getConfig() {
        return this.reader.getConfig();
    }


}
