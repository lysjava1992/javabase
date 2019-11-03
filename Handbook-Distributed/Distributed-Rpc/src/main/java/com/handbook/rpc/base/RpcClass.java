package com.handbook.rpc.base;

import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcClass
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:28
 * @Version 1.0
 **/
public class RpcClass implements Serializable{
    private static final long serialVersionUID = -7449415985785364582L;
    private String className;
    private String methodName;
    private Object[] params;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
