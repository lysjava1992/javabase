package com.learn.spring.base.chapter10;

import com.learn.spring.base.chapter10.bean.Customer;
import com.learn.spring.base.chapter10.dao.CustomerDao;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-18 14:40
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring_chapter10.xml")
public class Chapter10 {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void test(){
        List<Customer> list=new ArrayList<Customer>();
        for (int i = 0; i <10; i++) {
            Customer customer=new Customer();
            customer.setUsername("测试10"+i);

            customer.setPassword("密码10"+i);
            list.add(customer);
        }
        System.out.println(customerDao.addList(list));
    }


    @Test
    @Ignore
    public void test1(){
    Customer customer=new Customer();
    customer.setUsername("测试");
    customer.setPassword("111");
    System.out.println(customerDao.add(customer));
}
    @Test
    @Ignore
    public void test2(){

        System.out.println(customerDao.delete(1L));
    }


    @Test
    @Ignore
    public void test3(){
        Customer customer=new Customer();
        customer.setUsername("王五");
        customer.setPassword("111");
        customer.setId(20L);
        System.out.println(customerDao.update(customer));
    }
    @Test
    @Ignore
    public void test4(){

        System.out.println(customerDao.queryById(20L));
        System.out.println(customerDao.queryAll().size());
    }
}
