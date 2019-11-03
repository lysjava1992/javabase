package com.handbook.zookeeper.provide.tcp;

import com.handbook.zookeeper.base.ClassInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Proxy;
import java.net.Socket;
import java.util.List;
import java.util.Map; /**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName RpcSocketHandler
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/3 11:53
 * @Version 1.0
 **/
public class RpcSocketHandler implements Runnable {
    private Socket socket;
    private Map<String, Object> map;
    public RpcSocketHandler(Socket socket, Map<String, Object> map) {
        this.socket=socket;
        this.map=map;
    }

    public void run() {
        ObjectInputStream ois=null;
        ObjectOutputStream oos=null;
        try {
          ois=new ObjectInputStream(socket.getInputStream());
          oos=new ObjectOutputStream(socket.getOutputStream());
            ClassInfo classInfo= (ClassInfo) ois.readObject();
            Object result=invoke(classInfo);
            oos.writeObject(result);
            oos.flush();
            oos.close();
            ois.close();
            socket.close();
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

    /**
     * 反射调用方法
     * @param classInfo
     * @return
     */
    private Object invoke(ClassInfo classInfo) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class<?>[] types=null;
        if(classInfo.getParams()!=null&&classInfo.getParams().length>0){
            types=new  Class<?>[classInfo.getParams().length];
            for(int i=0;i<types.length;i++){
                types[i]=classInfo.getParams()[i].getClass();
            }
        }
        Object service=map.get(classInfo.getClassName());
       Method[] list= service.getClass().getMethods();
        Method method=service.getClass().getMethod(classInfo.getMethodName(),types);
        return method.invoke(service,classInfo.getParams());
    }
}
