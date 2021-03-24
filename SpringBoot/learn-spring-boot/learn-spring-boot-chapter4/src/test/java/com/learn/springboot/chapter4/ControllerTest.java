package com.learn.springboot.chapter4;

import com.learn.springboot.chapter4.entity.Result;
import com.sun.deploy.net.HttpResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {
     @Autowired
     private TestRestTemplate restTemplate;

     @Test
     void testGet(){
          String result=   restTemplate.getForObject("/get/ok",String.class);
          Assert.assertEquals(result,"OK");
          ResponseEntity<String>responseEntity= restTemplate.getForEntity("/get/ok",String.class);
          Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
     }

     @Test
     void testVoid(){
           restTemplate.getForObject("/get/void",String.class);
     }

     @Test
     void testPost(){
          ResponseEntity responseEntity=   restTemplate.postForEntity("/post","Tom", Result.class);
          Assert.assertEquals(responseEntity.getStatusCode(),HttpStatus.OK);
          Result result= (Result) responseEntity.getBody();
          Assert.assertEquals(result.getMsg(),"Tom");
     }

}
