package com.learn.springboot.chapter3;

import com.learn.springboot.chapter3.bean.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Date;
import java.util.Map;

@SpringBootApplication
public class LearnSpringBootChapter3Application {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(LearnSpringBootChapter3Application.class, args);
		Person person= (Person) context.getBean("person");
		System.out.println(person);
		Map<String,Object> map=person.getMaps();
		Map<String,Object> map2=person.getMaps2();
//		for(Map.Entry<String,Object> entry:map.entrySet()){
//			System.out.println(entry.getKey()+"--"+entry.getValue());
//		}
//		System.out.println();
//		for(Map.Entry<String,Object> entry:map2.entrySet()){
//			System.out.println(entry.getKey()+"--"+entry.getValue());
//		}
		System.out.println();
		Person2 person2= (Person2) context.getBean("person2");
		System.out.println(person2);
		System.out.println();

		Person3 person3= (Person3) context.getBean("person3");
		System.out.println(person3);

		Person4 person4= (Person4) context.getBean("person4");
		System.out.println(person4);



	}

}
