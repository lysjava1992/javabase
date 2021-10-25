package com.learn.shiro;

import com.alibaba.druid.DbType;
import com.alibaba.druid.pool.DruidDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

import javax.sql.DataSource;

public class JdbcShiro {
    public static void main(String[] args) {

        // 管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        DruidDataSource dataSource=new DruidDataSource();
        dataSource.setUrl("jdbc:sqlserver://192.168.1.6:1433;DatabaseName=Shiro");
        dataSource.setDbType(DbType.sqlserver);
        dataSource.setDriver(new SQLServerDriver());
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setUsername("sa");
        dataSource.setPassword("s9suk6u");
        // 设置数量源
        JdbcRealm realm=new JdbcRealm();
        realm.setDataSource(dataSource);
        defaultSecurityManager.setRealm(realm);

        // 绑定管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取主题
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("hangman1", "123456");
        try {
            subject.login(token);
            System.out.println("登录成功");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
        }
        subject.logout();
    }
}








