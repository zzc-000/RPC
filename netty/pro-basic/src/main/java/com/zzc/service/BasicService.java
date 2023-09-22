package com.zzc.service;

import com.alibaba.fastjson.JSONObject;
import com.zzc.netty.annotation.RemoteInvoke;
import com.zzc.netty.util.Response;
import com.zzc.user.model.User;
import com.zzc.user.remote.UserRemote;
import org.springframework.stereotype.Service;

@Service
public class BasicService {
    @RemoteInvoke
    private UserRemote userRemote;

    public void testSaveUser(){
        User user = new User();
        user.setId(1000);
        user.setName("张三");;
        Object response= userRemote.saveUser(user);
        System.out.println(JSONObject.toJSONString(response));
    }
}
