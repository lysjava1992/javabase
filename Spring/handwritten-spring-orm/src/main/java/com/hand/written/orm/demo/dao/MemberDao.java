package com.hand.written.orm.demo.dao;

import com.hand.written.orm.demo.model.Member;
import com.hand.written.orm.framework.BaseDaoSupport;
import com.hand.written.orm.framework.QueryRule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.core.common.jdbc.datasource.DynamicDataSource;
import javax.sql.DataSource;
import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MemberDao
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/11 16:52
 * @Version 1.0
 **/
@Repository
public class MemberDao extends BaseDaoSupport<Member,Long> {


    private JdbcTemplate template;



    @Override
    protected String getPKColumn() {
        return "id";
    }

    /**
     * 单一数据源
     * @param dataSource
     */
    @Override
    @Resource(name = "dataSource")
    protected void setDataSource(DataSource dataSource) {
        super.setDataSourceReadOnly(dataSource);
        super.setDataSourceWrite(dataSource);
    }


    @Override
    public boolean insert(Member member) throws Exception {
        return super.insert(member);
    }

    @Override
    public boolean update(Member member) throws Exception {
        return  super.update(member);
    }


    public Member selectOne(Long id) throws Exception {
        return super.get(id);
    }

    public List<Member> selectByQueryRule(QueryRule queryRule) throws Exception {
        return super.select(queryRule);
    }
}
