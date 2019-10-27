package com.handbook.chat.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 天行健，君子以自强不息
 * 地势坤，君子以厚德载物
 *
 * @ClassName ChatServerHandler
 * @Description TODO
 * @Author Mr.Luan
 * @Date 2019/10/27 11:02
 * @Version 1.0
 **/
public class ChatServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 管理channel的组，可以理解为channel的池
     */
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final AttributeKey<String> NICK_NAME = AttributeKey.valueOf("nickName");


    /**
     * 接收到客户端信息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String recive= (String) msg;
        System.out.println(msg);
        String[] request=recive.split("@");
        if(request.length==2){
            if(request[0].equals("LOGIN")){
                //注册
                ctx.writeAndFlush(checkName(request[1],ctx));
            }else {
                //广播消息
                broadcast(recive,true,null);
            }
        }
    }

    /**
     *下线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String response="SYSTEM@"+ctx.channel().attr(NICK_NAME).get()+"下上线";
        channels.remove(ctx.channel());
        System.out.println(response);
        broadcast(response,true,null);
    }

    /**
     * //服务端监听到客户端活动
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }



    /**
     * 异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        channels.remove(ctx.channel());
        ctx.close();
    }

    private void broadcast(String msg, boolean b,ChannelHandlerContext ctx) {
        if(b){
            for (Channel channel : channels) {
                if(channel.attr(NICK_NAME).get()!=null){
                    channel.writeAndFlush(msg);
                }
            }
        }else {
            for (Channel channel : channels) {
                if(channel.attr(NICK_NAME).get()!=null&&channel!=ctx.channel()){
                    channel.writeAndFlush(msg);
                }
            }
        }
    }

    /**
     *昵称存在 返回ERR
     * 不存在 加入channels
     * @param name
     * @param ctx
     * @return
     */
    private String checkName(String name,ChannelHandlerContext ctx) {
        for (Channel channel : channels) {
            //遍历ChannelGroup中的channel
            if(channel.attr(NICK_NAME).get()==null||channel.attr(NICK_NAME).get().equals(name)){
                return "ERR";
            }
        }
        ctx.channel().attr(NICK_NAME).set(name);
        channels.add(ctx.channel());
        String response="SYSTEM@"+name+"已上线";
        broadcast(response,false,ctx);
        return "OK";
    }

}
