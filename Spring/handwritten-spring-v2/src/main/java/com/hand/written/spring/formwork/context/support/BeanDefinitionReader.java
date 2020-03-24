package com.hand.written.spring.formwork.context.support;

import com.hand.written.spring.formwork.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 * 对配置文件 加载 读取 解析
 *
 * @ClassName BeanDefinitionReader
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/3 10:04
 * @Version 1.0
 **/
public class BeanDefinitionReader {
    private Properties config = new Properties();
    private List<String> registryBeanClass = new ArrayList<>();
    private final String SCAN_PACKAGE = "scan.package";

    public BeanDefinitionReader(String... locations) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(is);
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
        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    public List<String> loadBeadDefinitions() {
        return this.registryBeanClass;
    }

    /**
     * 注册一个bean 就返回一个BeanDefinition
     *
     * @param className
     * @return
     */
    public BeanDefinition registerBean(String className) {
        if (registryBeanClass.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            //全限定名
            beanDefinition.setClassName(className);
            //简化类名 在IOC中
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            return beanDefinition;
        }
        return null;
    }

    public Properties getConfig() {
        return this.config;
    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            String fullyClassName = packageName + "." + file.getName();
            if (file.isDirectory()) {
                doScanner(fullyClassName);
            } else {
                registryBeanClass.add(fullyClassName.replace(".class", ""));
            }
        }

    }

    private String lowerFirstCase(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}






