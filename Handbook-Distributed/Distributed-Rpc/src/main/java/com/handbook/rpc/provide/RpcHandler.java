package com.handbook.rpc.provide;

import com.handbook.rpc.base.RpcClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcHandler
 *  实际处理是 服务器接收客户端的请求
 *   根据  RpcClass 来反射调用 service的方法
 *   返回 结果
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:25
 * @Version 1.0
 **/
public class RpcHandler implements Runnable {
    private Socket socket;
    private Object service;
    public RpcHandler(Socket socket, Object service) {
        this.socket=socket;
        this.service=service;
    }

    public void run() {
        try {
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            RpcClass rpcClass= (RpcClass) ois.readObject();
            Object result=invoke(rpcClass);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
            oos.flush();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcClass rpcClass) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object[] params=rpcClass.getParams();

        Class<?>[] types=new Class<?>[params.length];
        for (int i=0;i<params.length;i++){
            types[i]=params[i].getClass();
        }
        Method method=service.getClass().getMethod(rpcClass.getMethodName(),types);
        return method.invoke(service,params);
    }
}
