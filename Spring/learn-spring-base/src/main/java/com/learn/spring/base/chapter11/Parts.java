package com.learn.spring.base.chapter11;

import java.util.UUID;

public class Parts {
    private String id;

    public Parts() {
        this.id= UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
