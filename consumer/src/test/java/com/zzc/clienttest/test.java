//package com.zzc.clienttest;
//
//import com.alibaba.fastjson.JSONObject;
//import com.zzc.netty.client.annotation.RemoteInvoke;
//import com.zzc.netty.client.param.Response;
//import com.zzc.netty.user.bean.User;
//import com.zzc.netty.user.remote.UserRemote;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@ComponentScan("\\")
//public class test {
//    @RemoteInvoke
//    private static UserRemote userRemote;
//
//
//    public static void main(String[] args) throws InterruptedException {
//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(test.class);
//        //context.wait();
//
//
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        Response response = userRemote.saveUser(u);
//        System.out.println(JSONObject.toJSONString(response));
//
//    }
//}
