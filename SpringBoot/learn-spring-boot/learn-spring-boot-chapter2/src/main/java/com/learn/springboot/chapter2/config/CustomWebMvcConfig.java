package com.learn.springboot.chapter2.config;

import com.learn.springboot.chapter2.http.message.PropertiesPersonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomWebMvcConfig
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/7/25 16:33
 * @Version 1.0
 **/
@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new PropertiesPersonHttpMessageConverter());
    }
}

















