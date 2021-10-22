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
@MapperScan(basePackages = "com.learn.springboot.chapter6.dao2",
            sqlSessionFactoryRef = "sqlSessionFactory2",
            sqlSessionTemplateRef ="sqlSessionTemplate2" )
public class MyBatisConfigTwo {

    @Resource(name = "dsTwo")
    DataSource dsTwo;

    @Bean
    SqlSessionFactory sqlSessionFactory2(){
         SqlSessionFactory sqlSessionFactory=null;
        try {
            SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
            bean.setDataSource(dsTwo);
            bean.setMapperLocations(new PathMatchingResourcePatternResolver().
                    getResources("classpath*:mapper2/*.xml"));
            sqlSessionFactory=bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  sqlSessionFactory;
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate2(){
        return new SqlSessionTemplate(sqlSessionFactory2());
    }
}
