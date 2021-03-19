package com.learn.lombok;
import lombok.Cleanup;
import java.io.FileInputStream;


public class CleanupExample {
    public static void main(String[] args) throws Exception {
       @Cleanup FileInputStream fis=new FileInputStream("F:\\.myeclipse.properties");
        int length=0;
        StringBuffer stringBuffer=new StringBuffer();
        while (length!=-1){
            length=fis.read();
            char b= (char) length;
            stringBuffer.append(b);
        }
        System.out.println(stringBuffer.toString());
    }
}
