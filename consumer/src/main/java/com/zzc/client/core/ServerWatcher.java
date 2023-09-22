package com.zzc.client.core;


import com.zzc.client.zk.ZooKeeperFactory;
import io.netty.channel.ChannelFuture;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;

import java.util.HashSet;
import java.util.List;

public class ServerWatcher implements CuratorWatcher {


    @Override
    public void process(WatchedEvent watchedEvent) throws Exception {
        CuratorFramework client = ZooKeeperFactory.create();
        String path = watchedEvent.getPath();
        client.getChildren().usingWatcher(this);
        List<String> serverPaths = client.getChildren().forPath(path);
//        TCPClient.realServerPath = new HashSet<String>();
        ChannelManager.clear();
        for(String serverPath:serverPaths) {
            String[] str = serverPath.split("#");
            ChannelManager.realServerPath.add(str[0]+"#"+str[1]);

        }
        ChannelManager.clear();
        for(String realServer:ChannelManager.realServerPath){
            String[] str = realServer.split("#");
            ChannelFuture channelFuture = TCPClient.b.connect(str[0],Integer.valueOf(str[1]));
            ChannelManager.add(channelFuture);
        }






    }
}
