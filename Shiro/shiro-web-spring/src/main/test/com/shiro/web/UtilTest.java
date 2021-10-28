package com.shiro.web;

import org.apache.shiro.crypto.hash.SimpleHash;

public class UtilTest {
    public static void main(String[] args) {
        System.out.println(new SimpleHash("md5","123456","LiSi",2).toHex());
        System.out.println(new SimpleHash("md5","123456","king",2).toHex());
        System.out.println(new SimpleHash("md5","123456","admin",2).toHex());
    }
}
