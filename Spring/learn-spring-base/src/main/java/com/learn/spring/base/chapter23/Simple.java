package com.learn.spring.base.chapter23;


import java.util.ArrayList;
import java.util.List;

public class Simple {
    private Integer random;
    private String defaultLocale;
    public List<Boolean> booleanList=new ArrayList<Boolean>();

    public Integer getRandom() {
        return random;
    }

    public void setRandom(Integer random) {
        this.random = random;
    }

    public String getDefaultLocale() {
        return defaultLocale;
    }

    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }
}
