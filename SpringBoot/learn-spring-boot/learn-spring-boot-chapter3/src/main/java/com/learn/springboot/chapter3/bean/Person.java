package com.learn.springboot.chapter3.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-23 11:03
 **/
@Data
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    private String lastName;
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private Map<String,Object> maps2;
    private List<Object> lists;
    private List<Object> lists2;
    private Dog dog;

    @Override
    public String toString() {
        return "{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps.size() +
                ", maps2=" + maps.size() +
                ", lists=" + lists.size()+
                ", lists=" + lists2.size()+
                ", dog=" + dog +
                '}';
    }
}
