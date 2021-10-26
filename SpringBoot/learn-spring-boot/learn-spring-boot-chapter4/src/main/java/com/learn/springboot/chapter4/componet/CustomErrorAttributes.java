package com.learn.springboot.chapter4.componet;

import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-26 12:45
 **/
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes{
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest,includeStackTrace);
        errorAttributes.put("custom","自定义");
        return errorAttributes;
    }
}
