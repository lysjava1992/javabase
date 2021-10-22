package com.learn.springboot.chapter1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author admin
 */
@SpringBootApplication
public class LearnSpringBootChapter1Application {
    /**
     * 默认启动
     * @param args
     */
	public static void main(String[] args) {
	    SpringApplication.run(LearnSpringBootChapter1Application.class, args);

	}

//    public static void main(String[] args) {
//        //server.port=0 向系统询问一个可用端口
//        new SpringApplicationBuilder(LearnSpringBootChapter1Application.class)
//                .properties("server.port=0")
//                .run(args);
//    }

//        public static void main(String[] args) {
//
//        SpringApplication springApplication=new SpringApplication(LearnSpringBootChapter1Application.class);
//        springApplication.run(args);
//
//    }

}
