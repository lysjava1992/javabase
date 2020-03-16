package com.handbook.java.lambda;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description:
 * @author: Mr.Luan
 *  java8 中的Date-Time API
 * @create: 2019-12-10 16:22
 **/
public class Chapter7 {
    public static void main(String[] args) {
        Date date=new Date();
        System.out.println(date);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println("当前时间:"+localDateTime);
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(localDateTime.format(dtf));

        LocalDate date1=localDateTime.toLocalDate();
        System.out.println("date1:"+date1);
    }
}
