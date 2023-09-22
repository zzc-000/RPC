package com.zzc.clienttest;


import com.zzc.client.annotation.RemoteInvoke;
import com.zzc.client.param.Response;
import com.zzc.user.remote.TestRemote;
import com.zzc.user.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RemoteInvokeTest.class)
@ComponentScan("\\")
public class RemoteInvokeTest {
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
		Object result = userremote.testUser(user);
		System.out.println(result.toString());
//		Long start = System.currentTimeMillis();
//		for(int i=1;i<10000;i++){
//			userremote.saveUser(user);
//		}
//		Long end = System.currentTimeMillis();
//		Long count = end-start;
//		System.out.println("总计时:"+count/1000+"秒");

	}


}
