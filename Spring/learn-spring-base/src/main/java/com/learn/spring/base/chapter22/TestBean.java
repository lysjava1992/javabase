package com.learn.spring.base.chapter22;

import java.util.Date;

public class TestBean {
    private Integer value;
    @IsMobile(required = true)
    private String phone;
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "value=" + value +
                ", phone='" + phone + '\'' +
                '}';
    }
}
