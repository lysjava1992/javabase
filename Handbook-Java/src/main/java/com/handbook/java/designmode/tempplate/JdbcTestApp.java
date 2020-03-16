package com.handbook.java.designmode.tempplate;

import com.handbook.java.designmode.tempplate.dao.MemberDao;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName JdbcTestApp
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 11:08
 * @Version 1.0
 **/
public class JdbcTestApp {
    public static void main(String[] args) {
        MemberDao memberDao=new MemberDao();
        memberDao.query();
    }
}
