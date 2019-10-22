package com.handbook.file;

import java.io.File;
import java.io.IOException;

/**
 * @description: File 用于 文件和目录 的CRUD
 *   API ：
 *      file.exists();                 文件/目录   是否存在
 *
 *      file.mkdir();                  创建目录   只能创建一层  F:/desktop/dir         有desktop才能创建dir
 *      file.mkdirs();                 创建目录   可创建多层   F:/desktop/dir/1/1/1    从desktop开始会一级一级自动创建
 *      file.createNewFile();          创建文件   只能在目录存在的情况下创建
 *
 *      file.delete();                 删除       文件可以直接删除   目录只能在其为空时才能删除（子目录 子文件都不能存在）
 *
 *      file.listFiles();              返回该目录下的一级文件/目录数组
 *      file.list()                    返回该目录下的一级文件/目录的名称数组
 * @author: Mr.Luan
 * @create: 2019-10-22 15:34
 **/
public class FileApi {
    public static void main(String[] args) {
        String path = "F:/desktop/dir";
        String filePath="F:/desktop/dir/file.txt";
        emptyFolder(path);
    }

    /**
     * 清空文件夹
     * 即删除目录下的所有子目录和文件
     *   获取当前目录下File[]
     *   文件，直接删除；
     *   目录，递归删除；
     * @param path
     */
    private static void emptyFolder(String path) {
        File file=new File(path);
        if(!file.exists()){ return; }
        File[] arra=file.listFiles();
        if(arra.length<=0){ file.delete();return; }
        for (File fi:arra) {
            if(fi.isDirectory()){
                emptyFolder(fi.getPath());
                fi.delete();
            }else {
                System.out.println("删除"+fi.getPath());
                fi.delete();
            }
        }
    }

    /**
     * 删除
     * @param path
     */
    private static void deleteSimple(String path) {
        File file=new File(path);
        file.delete();
    }

    /**
     * 创建目录
     * @param path
     */
    private static void createDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            // 只能创建一级
            file.mkdir();
        }
        System.out.println("创建目录完毕");
    }

    /**
     * 创建文件
     * @param filePath
     */
    private static void createFile(String filePath) {
        File file=new File(filePath);
        if(!file.exists()){
            try {
                file.createNewFile();
                System.out.println("创建文件完毕");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
