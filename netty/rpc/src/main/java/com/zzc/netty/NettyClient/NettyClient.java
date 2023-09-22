package com.zzc.netty.NettyClient;

import com.zzc.netty.handler.SimpleClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.AttributeKey;


public class NettyClient {
    public static void main(String[] args) throws Exception {
        String host= "localhost";
        int port =8001;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
           Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE,true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DelimiterBasedFrameDecoder(Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                    ch.pipeline().addLast(new StringDecoder());
                    ch.pipeline().addLast(new SimpleClientHandler());
                    ch.pipeline().addLast(new StringEncoder());
                }
            });

            ChannelFuture f = b.connect(host,port).sync();
            f.channel().writeAndFlush("hello server");
            f.channel().writeAndFlush("\r\n");
            f.channel().closeFuture().sync();
            Object result = f.channel().attr(AttributeKey.valueOf("ssssss")).get();
            System.out.println("获取到服务器返回的数据"+result);
        }finally {
            workerGroup.shutdownGracefully();
        }
    }
}


