package com.learn.spring.base.chapter12;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.UUID;

public class ScopeTest {
    private UUID uuid;
    public ScopeTest() {
        this.uuid=UUID.randomUUID();
    }
    public void sayId(){
        System.out.println(this.uuid);
    }
}
