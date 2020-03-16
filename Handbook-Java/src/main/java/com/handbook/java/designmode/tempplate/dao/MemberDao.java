package com.handbook.java.designmode.tempplate.dao;

import com.handbook.java.designmode.tempplate.JdbcTemplate;
import com.handbook.java.designmode.tempplate.RowMapper;
import com.handbook.java.designmode.tempplate.entity.Member;

import java.sql.ResultSet;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MemberDao
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 11:03
 * @Version 1.0
 **/
public class MemberDao {
    private JdbcTemplate jdbcTemplate=new JdbcTemplate(null);

    public List<?> query(){
        String sql="select * from t_member";
        return jdbcTemplate.executeQuery(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws Exception {
                Member member=new Member();
        member.setId(rs.getLong("id"));
        member.setName(rs.getString("name"));
        member.setAge(rs.getInt("age"));
        member.setAddr(rs.getString("addr"));
        return member;
            }
        },null);
    }
//    @Override
//    public Object processResult(ResultSet resultSet,int rowNum) throws SQLException {
//        Member member=new Member();
//        member.setId(resultSet.getLong("id"));
//        member.setName(resultSet.getString("name"));
//        member.setAge(resultSet.getInt("age"));
//        member.setAddr(resultSet.getString("addr"));
//        return member;
//    }
}
