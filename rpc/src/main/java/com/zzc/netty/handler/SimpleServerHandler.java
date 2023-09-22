package com.zzc.netty.handler;

import com.alibaba.fastjson.JSONObject;
import com.zzc.netty.util.Response;
import com.zzc.netty.handler.param.ServerRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class SimpleServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //ctx.channel().writeAndFlush("Netty server is ok\r\n");
        //ctx.channel().close();
        ServerRequest request = JSONObject.parseObject(msg.toString(), ServerRequest.class);
        Response resp = new Response();
        resp.setId(request.getId());
        resp.setResult("测试成功");
        ctx.channel().writeAndFlush(JSONObject.toJSONString(resp));
        ctx.channel().writeAndFlush("\r\n");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println("read idle");
                ctx.channel().close();
            }else if(event.state().equals(IdleState.WRITER_IDLE)){
                System.out.println("write idle");
            }else if(event.state().equals(IdleState.ALL_IDLE)){
                System.out.println("RW idle");
                ctx.channel().writeAndFlush("Ping\r\n");
                System.out.println("server ping");
            }

        }
    }
}
