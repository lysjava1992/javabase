package com.learn.springboot.chapter4;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.learn.springboot.chapter4.entity.Result;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testA() throws Exception {
        String result = mockMvc.perform(
                //路径与参数设置
                get("/mock/a")
                        .param("a", "hello")
                        .param("b", "world")
                        // 数据格式
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print()) //打印
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();//返回值
        Assert.assertEquals("helloworld", result);
    }

    @Test
    void testB() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/mock/b")
                        .param("a", "Hello")
                        .param("b", "Java")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        System.out.println(mvcResult.getResponse().getContentType());
        Assert.assertEquals("HelloJava", mvcResult.getResponse().getContentAsString());
    }

    @Test
    void testC() throws Exception {
        String content = "{\"name\":\"Tom\",\"age\":21,\"addr\":\"济南\"}";
        String msg = mockMvc.perform(post("/mock/c")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(content))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(msg);
    }

    @Test
    void testD() throws Exception {
     String str=mockMvc.perform(multipart("/file")
                         .file(new MockMultipartFile("file",
                                 "test.txt",
                                 "multipart/form-data",
                                 "文本内容".getBytes("UTF-8"))))
                          .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(str);
    }
}
