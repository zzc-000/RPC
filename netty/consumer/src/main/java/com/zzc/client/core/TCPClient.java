package com.zzc.client.core;

import com.alibaba.fastjson.JSONObject;
import com.zzc.client.constant.Constants;
import com.zzc.client.handler.SimpleClientHandler;
import com.zzc.client.param.ClientRequest;
import com.zzc.client.param.Response;
import com.zzc.client.zk.ZooKeeperFactory;
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
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.Watcher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TCPClient {
//    static  Set<String> realServerPath = new HashSet<String>();
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


        CuratorFramework client = ZooKeeperFactory.create();
        String host= "localhost";
        int port =8001;
        try {
            List<String> serverPaths = client.getChildren().forPath(Constants.SERVER_PATH);
            CuratorWatcher watcher = new ServerWatcher();
            //加上zk监听
            client.getChildren().usingWatcher(watcher).forPath(Constants.SERVER_PATH);

            for(String serverPath:serverPaths){
                String[] str = serverPath.split("#");
                //realServerPath.add(str[0]+"#"+str[1]);
                ChannelFuture channelFuture = TCPClient.b.connect(str[0],Integer.valueOf(str[1]));
                ChannelManager.add(channelFuture);
            }
            if(ChannelManager.realServerPath.size()>0){
                String[] hostAndPort = ChannelManager.realServerPath.toArray()[0].toString().split("#");
                host = hostAndPort[0];
                port = Integer.valueOf(hostAndPort[1]);

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

//        try{
//            f = b.connect(host,port).sync();
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }


    }
    //注意1：每一个请求都是同一个链接，并发问题-----
    //请求
    //唯一id识别
    //内容
    //相应
    //相应唯一识别
    //响应结果


    public static Response send(ClientRequest request){
        f= ChannelManager.get(ChannelManager.position);
        f.channel().writeAndFlush(JSONObject.toJSONString(request));
        f.channel().writeAndFlush("\r\n");
        DefaultFuture df = new DefaultFuture(request);
        return df.get();
    }
}






















