package com.handbook.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description:
 *  基于jdk原生io实现文件复制 或者转移
 * @author: Mr.Luan
 * @create: 2019-10-23 16:32
 **/
public class CopyFile {
    public static void main(String[] args) throws IOException {
         String path="F:\\desktop\\课程\\咕泡\\分布式\\06 分布式消息通信之ActiveMQ\\mic_20180708_分布式消息通信activemq原理分析(下)-.mp4";
         String copyPath="F:/desktop/dir/io/2";
         doCopyFile(path,copyPath,false);
    }
    private static void doCopyFile(String path, String copyPath, boolean delete) throws IOException {
        File file=new File(path);
        if("".equals(path)||"".equals(copyPath)){
            System.out.println("无效路径");
            return;
        }
        if(!file.exists()||file.isDirectory()){
            System.out.println("目标文件不存在");
            return;
        }
        File copyFile=new File(copyPath);
        if(!copyFile.exists()||!copyFile.isDirectory()){
           copyFile.mkdirs();
        }
        //获取文件名
        path=path.replace('\\','/');
        System.out.println(path);
        String[] arr=path.split("/");
        String name=arr[arr.length-1];
       //新建文件
        copyPath=copyPath+"/"+name;
        copyFile=new File(copyPath);
        copyFile.createNewFile();
        //复制文件
        long start=System.currentTimeMillis();
        FileInputStream fis=new FileInputStream(file);
        FileOutputStream fos=new FileOutputStream(copyFile);
        byte[] buffer=new byte[1024];
        int len=0;
        while ((len=fis.read(buffer))!=-1){
            fos.write(buffer,0,len);
        }
        fis.close();
        fos.close();
        if(delete){
            file.delete();
          //  deleteFile(file);
        }
        long end=System.currentTimeMillis();
        System.out.println("复制或移动完成,耗时【"+((end-start)/1000)+"S】");
    }

    private static void deleteFile(File file) {
        File[] arr=file.listFiles();
        if(arr.length<=0){return;}
        for (File fi:arr){
            if(fi.isDirectory()){
                deleteFile(fi);
                fi.delete();
            }else {
                fi.delete();
            }
        }
    }
}
