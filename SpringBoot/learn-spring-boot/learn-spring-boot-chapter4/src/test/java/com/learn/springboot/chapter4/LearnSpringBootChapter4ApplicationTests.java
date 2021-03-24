package com.learn.springboot.chapter4;

import com.learn.springboot.chapter4.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.logging.Handler;

@SpringBootTest
class LearnSpringBootChapter4ApplicationTests {

	@Autowired
	HelloService helloService;


	@Test
	void contextLoads() {
		helloService.sayHi();
	}


}
