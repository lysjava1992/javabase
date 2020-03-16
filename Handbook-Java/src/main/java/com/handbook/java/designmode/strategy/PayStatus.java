package com.handbook.java.designmode.strategy;

import lombok.Data;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName PayStatus
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/23 10:00
 * @Version 1.0
 **/
@Data
public class PayStatus {
    private int code;
    private String msg;
    private String data;

    public PayStatus(int code, String msg, String data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" + code +
                ";" + msg +
                ";" + data +
                '}';
    }
}
