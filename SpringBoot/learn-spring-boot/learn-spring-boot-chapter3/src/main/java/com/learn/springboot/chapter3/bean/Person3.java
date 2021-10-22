package com.learn.springboot.chapter3.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
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
public class Person3 {
    @Value("${person2.last-name}")
    private String lastName;

    @Value("#{3+15}")
    private Integer age;

    @Value("true")
    private Boolean boss;

    @Value("${person2.birth}")
    private Date birth;



    private Map<String,Object> maps;


    private List<Object> lists;


    private Dog dog;
    @Override
    public String toString() {
        return "{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                '}';
    }
}
