package com.shiro.web.service.impl;

import com.shiro.web.model.User;
import com.shiro.web.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    RowMapper<User> rowMapper=new RowMapper<User>() {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User u = new User();
            u.setId(rs.getInt(1));
            u.setUsername(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setRole(rs.getString(4));
            return u;
        }
    };
    @Override
    public User findByName(String name) {
        System.out.println("权限查询----------------------");
        User user=null;
          String selectSql = "select * from users where  username = ?";
          try {
              user= jdbcTemplate.queryForObject(selectSql, rowMapper,name);
          }catch (EmptyResultDataAccessException e){
          }
          if(user!=null){
              selectSql = "select a.identify from permission a, role_permission b " +
                      "where a.id=b.permissionId " +
                      "and b.role=?";
              List<String>permissions=  jdbcTemplate.query(selectSql, (rs, rowNum) -> rs.getString(1),user.getRole());
              user.setPermissions(new HashSet<>(permissions));

          }
        return user;
    }

    @Override
    public List<User> find() {

        String selectSql = "select * from users";
        List<User> query = jdbcTemplate.query(selectSql, rowMapper);
        return query;
    }

    @Override
    public User add(User user) {
        user.setPassword(processPassword(user));
        String sql = "INSERT INTO users(username,password,role) values(?,?,?);";
        jdbcTemplate.update(sql, user.getUsername(), user.getPassword(),user.getRole());
        return findByName(user.getUsername());
    }

    private String processPassword(User user) {
        return    new SimpleHash("md5",user.getPassword(),user.getUsername(),2).toHex();
    }

    @Override
    public User update(User user) {
        String sql = "update users  set password=?  where id=? ;";
        jdbcTemplate.update(sql,user.getPassword(),user.getId());
        return user;
    }

    @Override
    public void delete(int id) {
       String sql="delete from users where id=? ;";
        jdbcTemplate.update(sql,id);
    }

}
