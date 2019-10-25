package com.handbook.tomcat.bio;

import java.io.InputStream;
import java.io.OutputStream; /**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:16
 **/
public class MeHttpResponse {

    private OutputStream outputStream;
    public MeHttpResponse( OutputStream outputStream) {
        this.outputStream=outputStream;
    }
}
