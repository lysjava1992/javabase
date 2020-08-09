package com.learn.spring.cloud.config.client.config;


import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;


/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName MyPropertySourceLocator
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/8/9 15:53
 * @Version 1.0
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class MyPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String,Object> source=new HashMap<>();
        source.put("server.time","2020-08-09");
        MapPropertySource propertySource=new MapPropertySource("custom-property-2020",source);
        return propertySource;
    }
}
