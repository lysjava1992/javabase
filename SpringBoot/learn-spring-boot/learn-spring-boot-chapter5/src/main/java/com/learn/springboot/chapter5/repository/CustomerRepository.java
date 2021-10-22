package com.learn.springboot.chapter5.repository;

import com.learn.springboot.chapter5.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.*;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 16:19
 **/
@Repository
public class CustomerRepository {

    @Resource(name = "jdbcTemplateOne")
    JdbcTemplate jdbcTemplate;
    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    public Customer add(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement("insert into user " +
                                 "(username,password,create_time,addr) values (?,?,?,?);",
                                  Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, customer.getUsername());
                ps.setString(2, customer.getPassword());
                ps.setString(3, customer.getCreateTime());
                ps.setString(4, customer.getAddr());
                return ps;
            }
        }, keyHolder);
        customer.setId(keyHolder.getKey().longValue());
        return customer;
    }

    public List<Customer> selectList(int type) {
        if(type==2){
            return jdbcTemplateTwo.query("select * from user", new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                    long id = resultSet.getLong("id");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String createTime = resultSet.getString("create_time");
                    String addr = resultSet.getString("addr");
                    Customer customer=new Customer(id,username,password,createTime,addr);
                    return customer;
                }
            });
        }
        return jdbcTemplate.query("select * from user", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String createTime = resultSet.getString("create_time");
                String addr = resultSet.getString("addr");
                   Customer customer=new Customer(id,username,password,createTime,addr);
                return customer;
            }
        });
    }

    public Customer selectById(Integer id) {
    return jdbcTemplate.queryForObject("select * from user where id = ?", new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String createTime = resultSet.getString("create_time");
                String addr = resultSet.getString("addr");
                Customer customer=new Customer(id,username,password,createTime,addr);
                return customer;
            }
        }, id);
    }

    public Customer update(Customer customer) {
       int count= jdbcTemplate.update("update user set password=? where id=?",
                customer.getPassword(),customer.getId());
       return customer;
    }
}
