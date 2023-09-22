package com.zzc.user.remote;

import com.zzc.netty.annotation.Remote;
import com.zzc.netty.util.ResponseUtil;
import com.zzc.user.model.User;

import javax.annotation.Resource;
import java.util.List;


@Remote
public class TestRemoteImpl implements TestRemote{
    @Resource
    private com.zzc.user.service.UserService userService;
    public Object testUser(User user){
        userService.save(user);
        return ResponseUtil.createSuccessResults(user);
    }
    public Object testUsers(List<User> users){
        userService.saveList(users);
        return ResponseUtil.createSuccessResults(users);
    }
}
