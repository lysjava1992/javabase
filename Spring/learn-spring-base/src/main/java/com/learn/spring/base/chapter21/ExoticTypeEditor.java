package com.learn.spring.base.chapter21;

import org.springframework.beans.propertyeditors.CustomDateEditor;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public Object getValue() {
        ExoticType type= (ExoticType) super.getValue();
        return new ExoticType(type.getName().toLowerCase()) ;
    }
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        //配置文件中的String转为目标bean中的属性相应类型
        setValue(new ExoticType(text.toUpperCase()));
    }
}
