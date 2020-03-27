package com.learn.springboot.chapter6.component;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-27 10:50
 **/
@Configuration
@MapperScan(basePackages = "com.learn.springboot.chapter6.dao1",
            sqlSessionFactoryRef = "sqlSessionFactory1",
            sqlSessionTemplateRef ="sqlSessionTemplate1" )
public class MyBatisConfigOne {

    @Resource(name = "dsOne")
    DataSource dsOne;

    @Bean
    SqlSessionFactory sqlSessionFactory1(){
         SqlSessionFactory sqlSessionFactory=null;

        try {
            SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
            bean.setDataSource(dsOne);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                            getResources("classpath*:mapper1/*.xml"));

            sqlSessionFactory=bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  sqlSessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate1(){
        return new SqlSessionTemplate(sqlSessionFactory1());
    }
}
