package com.learn.oauth2.server4;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 13:32
 **/
public class TestApp2 {
    public static void main(String aa[]) throws Exception {

        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("oauth2.jks");

        KeyStore keyStore = KeyStore.getInstance("JKS");
        //这里填设置的keystore密码，两个可以不一致
        keyStore.load(inputStream, "123456".toCharArray());

        //加载别名，这里认为只有一个别名，可以这么取；当有多个别名时，别名要用参数传进来。不然，第二次的会覆盖第一次的
        Enumeration aliasEnum = keyStore.aliases();
        String keyAlias = "" ;
        while (aliasEnum.hasMoreElements()) {
            keyAlias = (String) aliasEnum.nextElement();
            System.out.println("【别名】 ："+keyAlias);
        }

        Certificate certificate = keyStore.getCertificate(keyAlias);

        //加载公钥
        PublicKey publicKey = keyStore.getCertificate(keyAlias).getPublicKey();
        String key = Base64.encodeBase64String(publicKey.getEncoded());
        System.out.println("【公钥】 ："+key);
        //加载私钥,这里填私钥密码
        PrivateKey privateKey = ((KeyStore.PrivateKeyEntry) keyStore.getEntry(keyAlias,
                new KeyStore.PasswordProtection("123456".toCharArray()))).getPrivateKey();

        //加载私钥另一写法
        //PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, "123456".toCharArray());

        //base64输出私钥
        String strKey = Base64.encodeBase64String(privateKey.getEncoded());
        System.out.println("【私钥】 ："+strKey);

        //测试签名
        String sign = Base64.encodeBase64String(sign("测试msg".getBytes(),privateKey,"SHA1withRSA",null));
        //测试验签
        boolean verfi = verify("测试msg".getBytes(),Base64.decodeBase64(sign), publicKey,"SHA1withRSA",null);
        System.out.println(verfi);

    }

    /**
     * 签名
     */
    public static byte[] sign(byte[] message, PrivateKey privateKey, String algorithm, String provider) throws Exception {
        Signature signature;
        if (null == provider || provider.length() == 0) {
            signature = Signature.getInstance(algorithm);
        } else {
            signature = Signature.getInstance(algorithm, provider);
        }
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }
    /**
     * 验签
     */
    public static boolean verify(byte[] message, byte[] signMessage, PublicKey publicKey, String algorithm,
                                 String provider) throws Exception {
        Signature signature;
        if (null == provider || provider.length() == 0) {
            signature = Signature.getInstance(algorithm);
        } else {
            signature = Signature.getInstance(algorithm, provider);
        }
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(signMessage);
    }
}
