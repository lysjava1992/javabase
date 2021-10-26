package com.learn.springboot.chapter5.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @description:
 *   @Qualifier  表示按名称查询
 * @author: Mr.Luan
 * @create: 2020-03-26 17:17
 **/
@Configuration
public class JdbcTemplateConfig {
    @Bean
    JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne")DataSource dsOne){
          return new JdbcTemplate(dsOne);
    }
    @Bean
    JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo")DataSource dsTwo){
        return new JdbcTemplate(dsTwo);
    }
}
