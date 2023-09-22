package com.zzc.user.remote;

import com.zzc.netty.annotation.Remote;
import com.zzc.user.bean.User;
import com.zzc.user.service.UserService;
import com.zzc.netty.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.List;
@Remote
public class TestRemoteImpl implements TestRemote{
    @Resource
    private UserService userService;
    public Object testUser(User user){
        UserService.save(user);
        return ResponseUtil.createSuccessResults(user);
    }
    public Object testUsers(List<User> users){
        UserService.saveList(users);
        return ResponseUtil.createSuccessResults(users);
    }
}
