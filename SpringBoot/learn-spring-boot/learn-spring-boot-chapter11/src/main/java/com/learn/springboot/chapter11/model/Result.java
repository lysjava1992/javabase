package com.learn.springboot.chapter11.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-02 16:50
 **/
@Data
public class Result  implements Serializable{
    private Serializable serializable= 1L;
    private Integer code;
    private String msg;
    private Object data;

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result() {

    }
}
