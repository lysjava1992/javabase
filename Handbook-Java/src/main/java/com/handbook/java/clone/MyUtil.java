package com.handbook.java.clone;

import java.io.*;
import java.rmi.AccessException;

public class MyUtil {
    private MyUtil(){
        throw  new AssertionError();
    }
    public static <T extends Serializable> T clone(T obj)throws Exception{
        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        ObjectOutputStream oos=new ObjectOutputStream(bout);
        oos.writeObject(obj);
        ByteArrayInputStream bin=new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream ois=new ObjectInputStream(bin);
        return (T) ois.readObject();
    }
}






