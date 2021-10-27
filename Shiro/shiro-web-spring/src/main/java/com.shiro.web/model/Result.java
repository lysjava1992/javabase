package com.shiro.web.model;

public class Result {
    private boolean status;
    private String msg;
    private Object data;

    public Result(boolean status) {
        this.status = status;
    }

    public Result(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
