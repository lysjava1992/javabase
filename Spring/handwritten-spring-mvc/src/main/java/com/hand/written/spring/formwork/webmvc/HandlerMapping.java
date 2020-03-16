package com.hand.written.spring.formwork.webmvc;

import java.lang.reflect.Method;
import java.util.regex.Pattern;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName HandlerMapping
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 16:32
 * @Version 1.0
 **/
public class HandlerMapping {
    private Object controller;
    private Method method;

    private Pattern pattern;

    public HandlerMapping(Pattern pattern, Object controller, Method method) {
        this.controller = controller;
        this.method = method;
       this.pattern=pattern;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }


}
