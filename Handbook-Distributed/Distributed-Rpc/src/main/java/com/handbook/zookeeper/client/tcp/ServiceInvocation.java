package com.handbook.zookeeper.client.tcp;

import com.handbook.zookeeper.base.ClassInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ServiceInvocation
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 12:32
 * @Version 1.0
 **/
public class ServiceInvocation {
    private ClassInfo classInfo;
    private String ip;
    private int port;
    public ServiceInvocation(ClassInfo classInfo, String ipAddress) {
        this.classInfo=classInfo;
        String arr[]=ipAddress.split(":");
        this.ip=arr[0];
        this.port=Integer.parseInt(arr[1]);
    }

    public Object invoke() {
        Object result=null;
        try {
            Socket socket=new Socket(ip,port);
            ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(classInfo);
            oos.flush();
            ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
            result=ois.readObject();
            ois.close();
            oos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
