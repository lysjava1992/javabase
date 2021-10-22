package com.learn.springboot.chapter9.dto;

import lombok.Data;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-03-30 13:19
 **/
@Data
public class Result {
    private  Integer code;
    private  String msg;
    private  Object data;

    public Result() {
    }

    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
