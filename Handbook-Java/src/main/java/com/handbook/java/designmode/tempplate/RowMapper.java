package com.handbook.java.designmode.tempplate;

import java.sql.ResultSet;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RowMapper
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 11:21
 * @Version 1.0
 **/
public interface RowMapper<T> {
    public T mapRow(ResultSet rs,int rowNum)throws Exception;
}







