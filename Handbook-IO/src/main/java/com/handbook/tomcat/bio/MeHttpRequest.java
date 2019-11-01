package com.handbook.tomcat.bio;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Mr.Luan
 * @create: 2019-10-25 16:16
 **/
public class MeHttpRequest {
    private String url;
    private InputStream inputStream;
    private String method;
    private Map<String,String> param=new HashMap<>();
    private String msg;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStringParam(String  key) {
        return param.get(key);
    }
    public String getStringParam() {
        return JSON.toJSONString(param);
    }
    public String getMsg() {
        return msg;
    }


    public MeHttpRequest() {
    }

    public MeHttpRequest(InputStream inputStream) throws IOException {
        this.inputStream=inputStream;
        byte[] arr=new byte[1024];
        int len=inputStream.read(arr);
        String msg=new String(arr,0,len);
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
        String[] arr=msg.split("\n");
        if(arr.length<0){
            return;
        }
        //GET /?name=King&age=15 HTTP/1.1
        //POST / HTTP/1.1
        //name=King&age=15
        String[]request= arr[0].split("/");
        this.method=arr[0].substring(0,arr[0].indexOf("/")).trim();
        String url=arr[0].substring(arr[0].indexOf("/")+1,arr[0].lastIndexOf("/"));
         if(this.method.equals("GET")){
             parseGet(url);
         }else if(this.method.equals("POST")){
             parsePost(url,arr[arr.length-1]);
         }


    }

    private void parsePost(String url, String msg) {
        url=url.replace("HTTP","").trim();
        this.url=url;
        parseParam(msg);
        System.out.println(msg);
    }

    private void parseParam(String msg) {
        if(msg==null||"".equals(msg)){
             return;
        }
        String[] arr=msg.split("&");
        for (String str:arr){
            String[] requests=str.split("=");
            if(requests.length!=2){
               this.msg="请求参数不合法";
            }else {
                param.put(requests[0],requests[1]);
            }
        }
    }

    private void parseGet(String url) {
        url=url.replace("HTTP","").trim();
        String[] arr=url.split("\\?");
        if(arr.length==2){
            this.url=arr[0];
            parseParam(arr[1]);
        }else {
            this.url=url;
        }

    }


    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
