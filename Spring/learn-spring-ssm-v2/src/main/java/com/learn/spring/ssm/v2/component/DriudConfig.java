package com.learn.spring.ssm.v2.component;
;
import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Data;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-24 11:22
 **/
@Configuration
@PropertySource("classpath:db.properties")
@MapperScan("com.learn.spring.ssm.v2.dao")
@EnableTransactionManagement
public class DriudConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${dbPool.initialSize}")
    private int initialSize;
    @Value("${dbPool.minIdle}")
    private int minIdle;
    @Value("${dbPool.maxActive}")
    private int maxActive;
    @Value("${dbPool.maxWait}")
    private int maxWait;
    @Value("${dbPool.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${dbPool.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${dbPool.validationQuery}")
    private String validationQuery;
    @Value("${dbPool.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${dbPool.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${dbPool.testOnReturn}")
    private boolean testOnReturn;
    @Value("${dbPool.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${dbPool.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${dbPool.filters}")
    private String filters;
    @Value("{dbPool.datasource.connectionProperties}")
    private String connectionProperties;
    /**
     * 配置数据源
     * @return
     * @throws SQLException
     */
    @Bean
    DataSource dataSource() throws SQLException {
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setFilters(filters);
        dataSource.setConnectionProperties(connectionProperties);

        return dataSource;
    }



    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() throws SQLException {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());



        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws IOException, SQLException, PropertyVetoException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mapper/*.xml"));


        return sqlSessionFactoryBean;
    }

}
