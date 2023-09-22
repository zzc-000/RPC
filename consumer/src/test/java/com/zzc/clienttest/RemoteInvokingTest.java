package com.zzc.clienttest;

import com.zzc.client.annotation.RemoteInvoke;
import com.zzc.user.remote.TestRemote;
import com.zzc.user.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = RemoteInvokingTest.class)
//@ComponentScan("com.zzc")
//public class RemoteInvokingTest {
//
//    public static List<User> list = new ArrayList<User>();
//    @RemoteInvoke
//    public static UserRemote userRemote;
//    public static User user;
//    public static Long count = 0l;
//
//    static{
//        user = new User();
//        user.setId(1000);
//        user.setName("张三");
//    }
//    @Test
//    public void testSaveUser(){
//        User u = new User();
//        u.setId(1);
//        u.setName("zzc");
//        Response response = userRemote.saveUser(u);
//        System.out.println(JSONObject.toJSONString(response));
//    }

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
//        Response response = userRemote.saveUsers(users);
//        System.out.println(JSONObject.toJSONString(response));
//    }
//
//}
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokingTest.class)
@ComponentScan("\\")
public class RemoteInvokingTest {
    public static List<User> list = new ArrayList<User>();
    @RemoteInvoke
    public static TestRemote userremote;
    public static User user;
    public static Long count = 0l;

    static{
        user = new User();
        user.setId(1000);
        user.setName("张三");
    }
    @Test
    public void testSaveUser(){
        User user = new User();
        user.setId(1000);
        user.setName("张三");
        userremote.testUser(user);
//		Long start = System.currentTimeMillis();
//		for(int i=1;i<10000;i++){
//			userremote.saveUser(user);
//		}
//		Long end = System.currentTimeMillis();
//		Long count = end-start;
//		System.out.println("总计时:"+count/1000+"秒");

    }


}
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes=RemoteInvokingTest.class)
//@ComponentScan("\\")
//public class RemoteInvokingTest {
//    public static List<User> list = new ArrayList<User>();
//    @RemoteInvoke
//    public static TestRemote userremote;
//    public static User user;
//    public static Long count = 0l;
//
//    static{
//        user = new User();
//        user.setId(1000);
//        user.setName("张三");
//    }
//    @org.junit.Test
//    public void testSaveUser(){
//        User user = new User();
//        user.setId(1000);
//        user.setName("张三");
//        userremote.testUser(user);
////		Long start = System.currentTimeMillis();
////		for(int i=1;i<10000;i++){
////			userremote.saveUser(user);
////		}
////		Long end = System.currentTimeMillis();
////		Long count = end-start;
////		System.out.println("总计时:"+count/1000+"秒");
//
//    }
//
//
//}