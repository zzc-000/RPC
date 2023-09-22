package com.zzc.netty.NettyClient;

import com.alibaba.fastjson.JSONObject;
import com.zzc.netty.handler.SimpleClientHandler;
import com.zzc.netty.util.Response;
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


public class TCPClient {

    static final Bootstrap b = new Bootstrap();
    static ChannelFuture f = null;
    static{
        EventLoopGroup workerGroup = new NioEventLoopGroup();
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
        String host= "localhost";
        int port =8001;
        try{
            f = b.connect(host,port).sync();
        }catch(InterruptedException e){
            e.printStackTrace();
        }


    }
    //注意1：每一个请求都是同一个链接，并发问题-----
    //请求
    //唯一id识别
    //内容
    //相应
    //相应唯一识别
    //响应结果
    public static Response send(ClientRequest request){

        f.channel().writeAndFlush(JSONObject.toJSONString(request));
        f.channel().writeAndFlush("\r\n");
        DefaultFuture df = new DefaultFuture(request);
        return df.get();
    }
}






















