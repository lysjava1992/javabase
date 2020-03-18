package com.learn.spring.base.chapter10.dao;

import com.learn.spring.base.chapter10.bean.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-18 14:44
 **/
@Repository
public class CustomerDao {
    @Resource(name="jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Resource(name="transactionTemplate")
    private TransactionTemplate  transactionTemplate;
    public int add(Customer customer) {
        String sql = "INSERT INTO user ( username,password)  VALUE (?,?)";
        return jdbcTemplate.update(sql, customer.getUsername(),customer.getPassword());
    }

    /**
     * 编程式事物
     * @param list
     * @return
     */
    public int addList(final List<Customer> list) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for (int i = 0; i <list.size() ; i++) {
                    Customer customer=list.get(i);
                    String sql = "INSERT INTO user ( username,password)  VALUE (?,?)";
                    jdbcTemplate.update(sql, customer.getUsername(),customer.getPassword());
                    //异常
                    if(i==3){i=5/0;}
                }
            }
        });

        return 1;
    }

    public int delete(Long id) {
        String sql = "DELETE FROM user WHERE id= ?";
        return jdbcTemplate.update(sql, id);
    }

    public int update(Customer customer) {
        String sql = "UPDATE user SET username=?,password=? WHERE  id = ?";
        return jdbcTemplate.update(sql, customer.getUsername(),customer.getPassword(),customer.getId());
    }

    public Customer queryById(Long id) {
        String sql = "SELECT * FROM user WHERE id= ?";
        Customer customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Customer>(Customer.class), id);
        return customer;
    }


    public List<Customer> queryAll(){
        String sql = "SELECT * FROM user";
        List<Customer> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Customer>(Customer.class));
        return query;
    }


}
