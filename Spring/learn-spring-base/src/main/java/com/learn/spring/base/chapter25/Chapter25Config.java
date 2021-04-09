<<<<<<< HEAD
package com.learn.spring.base.chapter25;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@ComponentScan("com.learn.spring.base.chapter25")
@Configuration
public class Chapter25Config {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://192.168.1.6:5432/test");
        return  dataSource;
    }
}
=======
package com.learn.spring.base.chapter25;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Chapter25Config
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2021/4/3 10:56
 * @Version 1.0
 **/
@EnableLoadTimeWeaving
@EnableAspectJAutoProxy
@ComponentScan("com.learn.spring.base.chapter25")
@Configuration
public class Chapter25Config {

}
>>>>>>> 9f719d1f3c36f64afdf53d32a9ce89827f8acba5
