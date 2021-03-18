package com.imooc;

import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName Application
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/12/13 16:00
 * @Version 1.0
 **/
@MapperScan(basePackages="com.imooc.mapper")
@ComponentScan(basePackages = {"com.imooc","org.n3r.idworker"})
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
