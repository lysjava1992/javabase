package com.learn.oauth2.server4;

import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 *
 * 【私钥】：sun.security.rsa.RSAPrivateCrtKeyImpl@ffee048a
 * 【公钥】：Sun RSA public key, 2048 bits
 *  modulus: 17813946725928422642236874133522284643852511482143780193969028973536019666983973420283069177226724126914565319881819207398976012455043004382986657017263505395694017048626042812460395316007754313388959983086668725376039882288931147046213780343923791599748825483256175923360396769430443176672713504542772901681195432049889461889050992670562222772757346986673014590169481097701067224514569651645031293445613539188539995124434206765836093287723774396889781262299405672143821647970632786493257167509960710716341428397631294811688567335693612633025630984109503963580878304810979767412409682961267880407594454297493630798971
 public exponent: 65537
 * @description:
 * @author: Mr.Luan
 * @create: 2020-04-08 11:30
 **/
public class TestApp {
    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = getPrivateKey("oauth2.jks", "123456", "oauth2");
        System.out.println("【私钥】："+privateKey);
        PublicKey publicKey = getPublicKey("oauth2.jks", "123456", "oauth2");
        System.out.println("【公钥】："+publicKey);

    }

    private static PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return (PrivateKey) keyStore.getKey("oauth2", "123456".toCharArray());

    }

    private static PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return keyStore.getCertificate("oauth2").getPublicKey();

    }

}
