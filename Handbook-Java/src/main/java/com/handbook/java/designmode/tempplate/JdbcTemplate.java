package com.handbook.java.designmode.tempplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName JdbcTemplate
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:52
 * @Version 1.0
 **/
public class JdbcTemplate {
    private DataSource dataSource;

    public JdbcTemplate(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    private PreparedStatement createPreparedStatement(Connection connection,String sql) throws SQLException {

        return connection.prepareStatement(sql);

    }
    private ResultSet executeQuery(PreparedStatement ps, Object[] values) throws SQLException {
        for (int i=0;i<values.length;i++){
            ps.setObject(i,values[i]);
        }
        return ps.executeQuery();

    }
    private void closeStatement(Statement statement) throws SQLException {
        statement.close();
    }
    private void closeResult(ResultSet rs) throws SQLException {
        rs.close();
    }
    private void closeConnection(Connection connection) throws SQLException {
        connection.close();
    }
    private List<?> parseResult(ResultSet resultSet,RowMapper rowMapper)throws Exception{
        List<Object> list=new ArrayList<>();
        int rowNum=1;
        while (resultSet.next()){
                 Object o=rowMapper.mapRow(resultSet,rowNum++);
                 list.add(o);
             }
             return list;
    }
    public List<?> executeQuery(String sql,RowMapper<?> rowMapper, Object[] values) {

        try {
            //获取连接
            Connection connection = this.getConnection();
            //创建SQL
            PreparedStatement ps=this.createPreparedStatement(connection,sql);
            //执行SQL
           ResultSet resultSet= this.executeQuery(ps,values);
            //解析
             List<?> list=this.parseResult(resultSet,rowMapper);
            //关闭结果集
           this.closeResult(resultSet);
            //关闭语句集
           this.closeStatement(ps);
            //关闭连接
            this.closeConnection(connection);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    public abstract Object processResult(ResultSet resultSet,int rowNum) throws SQLException;
}
