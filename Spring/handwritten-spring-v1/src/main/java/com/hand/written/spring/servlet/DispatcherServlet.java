package com.hand.written.spring.servlet;


import com.hand.written.demo.action.DemoAction;
import com.hand.written.spring.annotation.Autowried;
import com.hand.written.spring.annotation.Controller;
import com.hand.written.spring.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 * <p>
 * Spring的启动类
 *
 * @ClassName DispatcherServlet
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/2 9:24
 * @Version 1.0
 **/
public class DispatcherServlet extends HttpServlet {
    private Properties contextConfig = new Properties();

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    private List<String> classNames = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("--------------doPost调用--------------");
    }

    /**
     * 初始化
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //加载
        doScanner(contextConfig.getProperty("scan.package"));

        //注册
        doRegistry();

        //依赖注入
        doAutowired();

        DemoAction action= (DemoAction) beanMap.get("demoAction");
         action.query(null,null,"King");
        //SpringMVC  HandlerMapping url--method
        initHandlerMapping();

    }

    /**
     * 读取配置文件
     *
     * @param location classpath:application.properties
     */
    private void doLoadConfig(String location) {
        //spring中通过Reader去查找和定位
        //从当前类所在包下加载指定名称的文件，getClass是到当前列
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(location.replace("classpath:", ""));
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 解析获取
     *
     * @param packageName
     */
    private void doScanner(String packageName) {
        //根据包名 获取对应的文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File classDir = new File(url.getFile());

        //是文件夹递归   是文件提取组合全类名
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", ""));

            }
        }
    }

    /**
     * 注册
     * 通过反射 获取类 扫描注解
     */
    private void doRegistry() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String classname : classNames) {
                Class<?> clazz = Class.forName(classname);
                //扫描对应的注解
                if(clazz.isAnnotationPresent(Controller.class)){

                    String beanName=lowerFirstCase(clazz.getSimpleName());
                    //实际Spring中并不是一个对应的实例，而是beanDefinition
                   beanMap.put(beanName,clazz.newInstance());

                }else if(clazz.isAnnotationPresent(Service.class)){
                   Service service= clazz.getAnnotation(Service.class);
                    // 有beanName 使用beanName;默认类名
                    // 接口，使用类型的接口去自动注入
                    String beanName=service.value();
                    if("".equals(beanName.trim())){
                        beanName=lowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance=clazz.newInstance();
                    beanMap.put(beanName,instance);
                    //获取它实现的接口
                    Class<?>[] interfaces=clazz.getInterfaces();
                    for (Class<?>i:interfaces){
                        beanMap.put(i.getName(),instance);
                    }
                }else {continue;}
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    /**
     *  依赖注入
     *  Spring中通过getBean方法触发
     */
    private void doAutowired() {
        if(beanMap.isEmpty()){return;}
        for (Map.Entry<String,Object> entry:beanMap.entrySet()){
            //获取所有字段
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                if(!field.isAnnotationPresent(Autowried.class)){continue;}
                Autowried autowried=field.getAnnotation(Autowried.class);
                String beanName=autowried.value().trim();
                if("".equals(beanName.trim())){
                    beanName=field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),beanMap.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initHandlerMapping() {

    }

    private String lowerFirstCase(String str){
       char[] chars=str.toCharArray();
       chars[0]+=32;
       return String.valueOf(chars);
    }

}
