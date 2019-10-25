package com.handbook.tomcat.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream; /**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:16
 **/
public class MeHttpRequest {
    private String url;
    private InputStream inputStream;
    private String method;
    public MeHttpRequest() {
    }

    public MeHttpRequest(InputStream inputStream) throws IOException {
        this.inputStream=inputStream;
        byte[] arr=new byte[1024];
        int len=inputStream.read(arr);
        String msg=new String(arr,0,len);
        System.out.println(msg);
        parseHttp(msg);
    }

    /** 解析Http q请求
    *  GET /  ms/kk?name=king HTTP  /1.1
    *  GET /    HTTP/1.1
    *  Host: localhost:8080
    *  Connection: keep-alive
    *  Upgrade-Insecure-Requests: 1
    *  User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36
    *  Sec-Fetch-Mode: navigate
    *  Sec-Fetch-User: ?1
    *  Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,;q=0.8,application/signed-exchange;v=b3
    *  Purpose: prefetch
    *  Sec-Fetch-Site: none
    *  Accept-Encoding: gzip, deflate, br
    *  Accept-Language: zh-CN,zh;q=0.9
    *  Cookie: grafana_session=fc1d55506a25f18589a27633872b9704
    * @param msg
    */
    private void parseHttp(String msg) {
    }


    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
