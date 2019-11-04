package com.handbook.rpc.client;

import com.handbook.rpc.base.RpcClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName SocketUtil
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/11/2 17:41
 * @Version 1.0
 **/
public class TCPTransport {
    private String host;
    private int port;
    private Socket socket;
    public TCPTransport(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket=new Socket(host,port);
    }

    public Object sendMsg(RpcClass rpcClass) throws IOException, ClassNotFoundException {
        ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(rpcClass);
        oos.flush();
        ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
        Object object=ois.readObject();
        oos.close();
        ois.close();
      return object;
    }
}
