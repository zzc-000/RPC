//package com.zzc;
//
//import com.zzc.netty.NettyClient.ClientRequest;
//import com.zzc.netty.NettyClient.TCPClient;
//import com.zzc.netty.annotation.RemoteInvoke;
//import com.zzc.netty.user.bean.User;
//import com.zzc.netty.user.remote.UserRemote;
//import com.zzc.netty.util.Response;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.stereotype.Component;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RemoteInvokingTest.class)
//@ComponentScan("com.zzc")
//public class RemoteInvokingTest {
//
//    @RemoteInvoke
//    private UserRemote userRemote;
//    @Test
//    public void testSaveUser(){
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        userRemote.saveUser(u);
//    }
//
//    @Test
//    public void testSaveUsers(){
//        List<User> users = new ArrayList<User>();
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        users.add(u);
//        User u1 = new User();
//        u1.setId(2);
//        u1.setName("zzc1");
//        users.add(u1);
//        userRemote.saveUsers(users);
//    }
//}
