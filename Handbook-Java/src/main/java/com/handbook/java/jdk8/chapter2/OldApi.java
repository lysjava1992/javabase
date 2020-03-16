package com.handbook.java.jdk8.chapter2;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName OldApi
 *      Date :      java.sql.Date   java.util.Date
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 9:00
 * @Version 1.0
 **/
public class OldApi {
    public static void main(String[] args) {

        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));


    }
}
