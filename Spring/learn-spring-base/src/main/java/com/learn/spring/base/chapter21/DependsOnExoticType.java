package com.learn.spring.base.chapter21;

public class DependsOnExoticType {
    private ExoticType type;
    public void setType(ExoticType type) {
        this.type = type;
    }
    public ExoticType getType() {
        return type;
    }
    public String getTypeString() {
        return type.getName();
    }
}
