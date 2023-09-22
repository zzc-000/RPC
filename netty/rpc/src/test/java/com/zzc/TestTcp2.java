//package com.zzc;
//
//import com.zzc.netty.NettyClient.ClientRequest;
//import com.zzc.netty.util.Response;
//import com.zzc.netty.NettyClient.TCPClient;
//import com.zzc.netty.user.bean.User;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestTcp2 {
////    @Test
////    public void testGetResponse(){
////        ClientRequest request = new ClientRequest();
////        request.setContent("测试TCP连接请求");
////        Response resp = TCPClient.send(request);
////        System.out.println(resp.getResult());
////    }
//
//    @Test
//    public void testSaveUser(){
//        ClientRequest request = new ClientRequest();
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        request.setCommand("com.zzc.netty.user.controller.UserController.saveUser");
//        request.setContent(u);
//        Response resp = TCPClient.send(request);
//        System.out.println(resp.getResult());
//    }
//
//    @Test
//    public void testSaveUsers(){
//        ClientRequest request = new ClientRequest();
//        List<User> users = new ArrayList<User>();
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        users.add(u);
//        User u1 = new User();
//        u1.setId(2);
//        u1.setName("zzc1");
//        users.add(u1);
//        request.setCommand("com.zzc.netty.user.controller.UserController.saveUsers");
//        request.setContent(users);
//        Response resp = TCPClient.send(request);
//        System.out.println(resp.getResult());
//    }
//}
