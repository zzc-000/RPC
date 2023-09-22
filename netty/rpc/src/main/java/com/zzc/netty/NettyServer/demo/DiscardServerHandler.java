package com.zzc.netty.NettyServer.demo;


import io.netty.buffer.ByteBuf;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
//        // Discard the received data silently.
//        ((ByteBuf) msg).release(); // (3)
//    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            ctx.channel().writeAndFlush("is ok \r\n");
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}