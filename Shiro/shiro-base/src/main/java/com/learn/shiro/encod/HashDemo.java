package com.learn.shiro.encod;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

/**
 * 散列
 */
public class HashDemo {
    public static void main(String[] args) {
    //    System.out.println(new Md5Hash("Hello").toString());
   System.out.println("--"+new SimpleHash("MD5","123456","101",1).toString());

        DefaultHashService hashService = new DefaultHashService();
        //默认算法SHA-512
        hashService.setHashAlgorithmName("SHA-512");

        //私盐，默认无
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        //是否生成公盐，默认false
        hashService.setGeneratePublicSalt(true);
        //用于生成公盐。默认就这个
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //生成Hash值的迭代次数
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(1).build();

        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);

        HashRequest request2 = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();

        String hex2 = hashService.computeHash(request2).toHex();
        System.out.println(hex2);
    }
}
