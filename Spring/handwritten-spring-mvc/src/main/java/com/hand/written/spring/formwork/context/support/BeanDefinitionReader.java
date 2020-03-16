package com.hand.written.spring.formwork.context.support;

import com.hand.written.spring.formwork.beans.BeanDefinition;
import org.omg.PortableInterceptor.INACTIVE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *       负责配置文件的解析
 * @ClassName BeanDefinitionReader
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 9:40
 * @Version 1.0
 **/
public class BeanDefinitionReader {
    /**
     * 要解析的包
     */
    private final String SCAN_PACKAGE="scan.package";
    /**
     * 配置文件封装成的Properties
     */
    List<Properties> propertiesList=new ArrayList<>();
    /**
     * 扫描包内的所有类
     */
    List<String> beanClassList=new ArrayList<>();

    public List<String> getBeanClassList() {
        return this.beanClassList;
    }

    /**
     * 初始化 封装配置文件
     * @param configLocations
     */
    public BeanDefinitionReader(String... configLocations) {

        for (String location:configLocations){

            Properties properties=new Properties();

            InputStream inputStream=this.getClass().getClassLoader()
                      .getResourceAsStream(location.replace("classpath:",""));
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(null==inputStream){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            propertiesList.add(properties);

        }
        try {

            doScanner();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描配置文件 获取需要扫描的包
     * @throws Exception
     */
    private void doScanner() throws Exception {

        if (propertiesList.isEmpty()){ throw new Exception("无有效配置文件"); }
        for (Properties properties:propertiesList){
            String packageName=  properties.getProperty(SCAN_PACKAGE);
            if(packageName==null){continue;}
            doScannerPackage(packageName);
        }
    }

    /**
     * 扫描包
     * @param packageName
     */
    private void doScannerPackage(String packageName) {
        URL url=this.getClass().getClassLoader().
                getResource("/"+packageName.replaceAll("\\.","/"));
        File classFile=new File(url.getFile());
        for (File file:classFile.listFiles()){
            //拼接全名 .class
            String fullyName=packageName+"."+file.getName();
             if(file.isDirectory()){
                 //是文件 继续递归扫描
                 doScannerPackage(fullyName);
             }else {
                 //整理成全限定类名
                  beanClassList.add(fullyName.replace(".class",""));
             }
        }
    }

    /**
     * 注册bean信息
     * @param className
     * @return
     */
    public BeanDefinition registerBean(String className) {
        if(beanClassList.contains(className)){
            BeanDefinition beanDefinition=new BeanDefinition();

            beanDefinition.setCassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase
                    (className.substring(className.lastIndexOf(".")+1))
                      );
            return beanDefinition;
        }
        return null;
    }

    private String lowerFirstCase(String str) {
        char[] chars= str.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return propertiesList.get(0);
    }
}
