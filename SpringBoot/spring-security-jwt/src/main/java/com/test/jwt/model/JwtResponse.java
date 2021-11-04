package com.test.jwt.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {
    private boolean status;
    private String msg;
    private String token;
    public JwtResponse() {
    }
    public JwtResponse(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public JwtResponse(boolean status, String msg, String token) {
        this.status = status;
        this.msg = msg;
        this.token = token;
    }
}
