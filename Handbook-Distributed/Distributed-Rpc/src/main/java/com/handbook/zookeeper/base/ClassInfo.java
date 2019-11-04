package com.handbook.zookeeper.base;

import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ClassInfo
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 10:42
 * @Version 1.0
 **/
public class ClassInfo implements Serializable{
    private static final long serialVersionUID = 2114879315570087778L;
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
