package com.zzc.netty.handler;



import com.alibaba.fastjson.JSONObject;
import com.zzc.netty.NettyClient.DefaultFuture;
import com.zzc.netty.util.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;


public class SimpleClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if("Ping".equals(msg.toString())){
            ctx.channel().writeAndFlush("Ping\r\n");
            System.out.println("client ping");
            return;
        }
        ctx.channel().attr(AttributeKey.valueOf("ssssss")).set(msg);
        Response response = JSONObject.parseObject(msg.toString(),Response.class);
        DefaultFuture.receive(response);
        //ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }


}
