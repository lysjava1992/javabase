package com.learn.spring.base.chapter25.mapper;

import com.learn.spring.base.chapter25.entity.Actor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;


public class ActorMappingQuery extends MappingSqlQuery<Actor> {
    public ActorMappingQuery(DataSource ds) {
        super(ds, "select id, first_name, last_name from t_actor where id = ?");
        declareParameter(new SqlParameter("id", Types.INTEGER));
        compile();
    }

    /**
     * 重写方法，查询返回的行与java对象的解析转换
     * @param resultSet  返回结果
     * @param rowNumber  第几行
     * @return
     * @throws SQLException
     */
    @Override
    protected Actor mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        Actor actor=new Actor();
        actor.setId(resultSet.getLong("id"));
        actor.setFirstName(resultSet.getString("first_name"));
        actor.setLastName(resultSet.getString("last_name"));
        return actor;
    }
}
