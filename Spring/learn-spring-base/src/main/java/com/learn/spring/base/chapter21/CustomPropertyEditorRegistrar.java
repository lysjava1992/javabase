package com.learn.spring.base.chapter21;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

//java代码注册方式
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // registry.registerCustomEditor(ExoticType.class, new ExoticTypeEditor());

    }
}
