package com.simple.thingsboard.test.controller;

import com.simple.thingsboard.server.ServerApplication;
import com.simple.thingsboard.server.common.data.asset.Asset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerApplication.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AssetControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSave(){
        Asset asset=new Asset();
        asset.setName("A楼");
        asset.setType("TEST");
        ResponseEntity responseEntity=  restTemplate.postForEntity("/api/asset",asset, Asset.class);
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Asset result= (Asset) responseEntity.getBody();
        System.out.println(result);
    }
}
