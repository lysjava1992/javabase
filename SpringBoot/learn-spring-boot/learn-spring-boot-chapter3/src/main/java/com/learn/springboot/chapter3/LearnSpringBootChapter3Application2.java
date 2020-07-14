package com.learn.springboot.chapter3;

import com.learn.spring.boot.starter.HelloService;
import com.learn.springboot.chapter3.bean.Person;
import com.learn.springboot.chapter3.bean.Person2;
import com.learn.springboot.chapter3.bean.Person3;
import com.learn.springboot.chapter3.bean.Person4;
import com.learn.springboot.chapter3.service.XmlService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Map;


@SpringBootApplication
public class LearnSpringBootChapter3Application2 {

	public static void main(String[] args) {
		ApplicationContext context=SpringApplication.run(LearnSpringBootChapter3Application2.class, args);
		Person person= (Person) context.getBean("person");
		System.out.println(person);

		//2.0 通过binder可以更容易的处理对象与多套配置的选择
		Binder binder=Binder.get(context.getEnvironment());
		Person person2=binder.bind("person", Bindable.of(Person.class)).get();
		System.out.println(person2);


	}

}
