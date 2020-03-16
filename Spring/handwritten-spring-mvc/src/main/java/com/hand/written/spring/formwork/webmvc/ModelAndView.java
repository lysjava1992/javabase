package com.hand.written.spring.formwork.webmvc;

import javax.jws.Oneway;
import java.util.Map;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ModelAndView
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/2/4 16:27
 * @Version 1.0
 **/
public class ModelAndView {
    private String viewName;
    private Map<String,Object> model;

    public ModelAndView(String viewName, Map<String, Object> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }
}
