package com.zzc;

import com.zzc.netty.NettyClient.ClientRequest;
import com.zzc.netty.util.Response;
import com.zzc.netty.NettyClient.TCPClient;
import org.junit.jupiter.api.Test;

public class TestTCP {
    @Test
    public void testGetResponse(){
        ClientRequest request = new ClientRequest();
        request.setContent("测试TCP连接请求");
        Response resp = TCPClient.send(request);
        System.out.println(resp.getResult());
    }
}
