package com.learn.springboot.chapter9;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class LearnSpringBootChapter9ApplicationTests {

	@Test
	void contextLoads() {
		BCryptPasswordEncoder  bcpe=new BCryptPasswordEncoder();
System.out.println(bcpe.encode("456789"));

	}

}
