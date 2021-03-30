package com.learn.spring.base.chapter11;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class ReplacementComputeValue implements MethodReplacer {
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
        String input= (String) args[0];
        return "方法替换";
    }
}
