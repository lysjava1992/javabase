package com.learn.spring.base.chapter20.beanWrapper;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyValue;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName BeanWrapperTest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/10/30 20:51
 * @Version 1.0
 **/
public class BeanWrapperTest {
    public static void main(String[] args) {
        BeanWrapper company = new BeanWrapperImpl(new Company());
        // set name属性
        company.setPropertyValue("name", "Some Company Inc.1");
        // 设置属性
        PropertyValue value = new PropertyValue("name", "Some Company Inc.2");
        company.setPropertyValue(value);
        // 设置泛型
        BeanWrapper jim = new BeanWrapperImpl(new Employee());
        jim.setPropertyValue("name", "Jim Stravinsky");
        company.setPropertyValue("managingDirector", jim.getWrappedInstance());
        System.out.println(  company.getPropertyValue("name"));
        // 获取属性
        Float salary = (Float) company.getPropertyValue("managingDirector.salary");
    }
}
