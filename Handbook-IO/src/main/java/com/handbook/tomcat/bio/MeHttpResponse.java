package com.handbook.tomcat.bio;

import java.io.FileInputStream;
import java.io.IOException;
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
    public void  write(String msg) throws IOException {
             StringBuilder respons=new StringBuilder();
             respons.append("HTTP/1.1 200 OK \n")
             .append("Content-Type: text/json \n")
             .append("ex")
             .append("\r\n")
          //   .append("<html><body>")
             .append(msg);
          //   .append("</body></html>") ;
        outputStream.write(respons.toString().getBytes());
        outputStream.close();
    }
    public void writeFile(String uri) throws IOException {
        System.out.println(uri);
        String contextType = "text/html;";
        if(uri.endsWith(".css")){
            contextType = "text/css;";
        }else if(uri.endsWith(".js")){
            contextType = "text/javascript;";
        }else if(uri.toLowerCase().matches("(jpg|png|gif)$")){
            String ext = uri.substring(uri.lastIndexOf("."));
            contextType = "image/" + ext;
        }
        StringBuilder respons=new StringBuilder();
        respons.append("HTTP/1.1 200 OK \n")
                .append("Content-Type: "+contextType+" \n")
                .append("\r\n");
        outputStream.write(respons.toString().getBytes());
        FileInputStream fis=new FileInputStream(uri);
        byte[] arr=new byte[1024*8];
        int len=0;
        while ((len=fis.read(arr))!=-1){
            outputStream.write(arr,0,len);
        }
        outputStream.close();
    }
}
