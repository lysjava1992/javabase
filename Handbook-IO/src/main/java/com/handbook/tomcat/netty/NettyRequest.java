package com.handbook.tomcat.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName NettyRequest
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/27 15:41
 * @Version 1.0
 **/
public class NettyRequest {
    private ChannelHandlerContext ctx;
    private FullHttpRequest request;
    private HttpMethod method;
    private String url;
    private Map<String,Object> paramMap=new HashMap<>();
    public  Object getParam(String key){
        return paramMap.get(key);
    }
    public Map<String,Object> getParam(){
        return paramMap;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public NettyRequest(ChannelHandlerContext ctx, FullHttpRequest request) throws IOException {
        this.ctx=ctx;
        this.request=request;
        this.method=request.method();
        this.url=request.uri();
        parseParam();
    }

    private void parseParam() throws IOException {
        if(method==HttpMethod.GET){
            //解析请求参数从路径中
            QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
            decoder.parameters().entrySet().forEach( entry -> {
                // entry.getValue()是一个List, 只取第一个元素
                paramMap.put(entry.getKey(), entry.getValue().get(0));
            });
        }else if(method==HttpMethod.POST) {
            HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(request);
            decoder.offer(request);

            List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();
            for (InterfaceHttpData parm : parmList) {
                Attribute data = (Attribute) parm;
                paramMap.put(data.getName(), data.getValue());
            }
        }
    }

}
