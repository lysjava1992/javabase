package com.learn.spring.base.chapter9;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-18 13:51
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring_chapter9.xml")
public class Chapter9 {
    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        jdbcTemplate.update("insert into user (username, password) VALUES (?,?)","张三","李四");
    }

}
