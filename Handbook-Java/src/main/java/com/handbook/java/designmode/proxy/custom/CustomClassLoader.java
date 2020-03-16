package com.handbook.java.designmode.proxy.custom;

import java.io.*;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName CustomClassLoader
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2020/1/12 16:21
 * @Version 1.0
 **/
public class CustomClassLoader  extends ClassLoader{
    private File classPathFile;
    public CustomClassLoader(){
        String classPath=CustomClassLoader.class.getResource("").getPath();
        this.classPathFile=new File(classPath);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String className=CustomClassLoader.class.getPackage().getName()+"."+name;
        if(classPathFile!=null){
            File classFile= new File(classPathFile,name.replace("\\.","/")+".class");
            if (classFile.exists()){
                FileInputStream in=null;
                ByteArrayOutputStream out=null;

                try {
                    in=new FileInputStream(classFile);
                    out=new ByteArrayOutputStream();
                    byte[] buff=new byte[1024];
                    int len;
                    while ((len=in.read(buff))!=-1){
                        out.write(buff,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(null!=in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(null!=out){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
