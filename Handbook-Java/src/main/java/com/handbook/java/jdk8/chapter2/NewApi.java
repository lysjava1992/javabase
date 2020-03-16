package com.handbook.java.jdk8.chapter2;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName NewApi
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/12/22 9:00
 * @Version 1.0
 **/
public class NewApi {
    public static void main(String[] args) {
        //yyyy-MM-dd固定格式
        LocalDate localDate=LocalDate.now();
        System.out.println(localDate);

        //hh:mm:ss.zzz
        LocalTime localTime=LocalTime.now();
        System.out.println(localTime);

        //yyyy-MM-dd  hh:mm:ss.zzz
        LocalDateTime localDateTime=LocalDateTime.now();
        System.out.println(localDateTime);

        //DateTimeFormatter.ofPattern()格式化
        System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")));

        //日历操作
        localDateTime=LocalDateTime.of(2017,10,15,13,15);

        System.out.println(localDateTime);

       //LocalDateTime转为自定义的时间格式的字符串
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt=LocalDateTime.now();
        System.out.println("时间(字符串)："+ldt.format(dtf));

        //LocalDateTime转long
        ZoneId zone = ZoneId.systemDefault();
        System.out.println("时区： "+zone);
        System.out.println("时间戳"+ldt.atZone(zone).toInstant());
        System.out.println("秒："+ldt.toEpochSecond(ZoneOffset.of("+8")));
        System.out.println("毫秒："+ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli());

        //字符串转 LocalDateTime
        String str="2019-10-01 10:10:10";
        System.out.println(LocalDateTime.parse(str,dtf));

        //long转LocalDateTime
        long time=System.currentTimeMillis();
        Instant instant = Instant.ofEpochMilli(time);
        System.out.println(LocalDateTime.ofInstant(instant,zone));
     }
}
