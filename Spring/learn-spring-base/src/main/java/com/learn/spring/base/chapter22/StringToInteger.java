package com.learn.spring.base.chapter22;

import org.springframework.core.convert.converter.Converter;

public class StringToInteger implements Converter<String,Integer> {
    public Integer convert(String source) {
        return Integer.parseInt(source)*10;
    }
}
