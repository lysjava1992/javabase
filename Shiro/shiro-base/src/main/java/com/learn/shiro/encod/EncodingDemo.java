package com.learn.shiro.encod;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.H64;
import org.apache.shiro.codec.Hex;

import java.nio.charset.StandardCharsets;

/**
 * 编码
 */
public class EncodingDemo {
    public static void main(String[] args) {
        String str="hello";
        String encod=Base64.encodeToString(str.getBytes(StandardCharsets.UTF_8));
        str=Base64.decodeToString(encod);
        System.out.println( encod);
        System.out.println( str);
        String encod2= Hex.encodeToString(str.getBytes(StandardCharsets.UTF_8));
        str=new String(Hex.decode(encod2));
        System.out.println( encod);
        System.out.println( str);
    }
}
