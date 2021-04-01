package com.learn.spring.base.chapter22;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;
import org.springframework.core.convert.converter.Converter;

import java.util.Collections;
import java.util.Set;

public class StringToInteger2 implements ConditionalGenericConverter {
    /**
     * 判断是否转换的条件
     * @param sourceType
     * @param targetType
     * @return
     */
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {

        return sourceType.getType()==String.class;
    }
    /**
     * 告诉转换器总部，我这个转换器支持什么样的转换，可以支持多个转换
     * 此处支持 string -> list 和 string -> map
     */
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(String.class,Integer.class));
    }

    /**
     *  转换
     * @param source
     * @param sourceType
     * @param targetType
     * @return
     */
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
              if(sourceType.getType()==String.class&&targetType.getType()==Integer.class){
                  return Integer.parseInt((String) source);
              }
        return null;
    }
}
