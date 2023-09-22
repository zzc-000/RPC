package com.zzc.user.remote;

import com.zzc.netty.annotation.Remote;
import com.zzc.user.bean.User;
import com.zzc.user.service.UserService;
import com.zzc.netty.util.ResponseUtil;

import javax.annotation.Resource;
import java.util.List;
@Remote
public class UserRemoteImpl implements UserRemote{
    @Resource
    private UserService userService;
    @Override
    public Object saveUser(User user){
        UserService.save(user);
        return ResponseUtil.createSuccessResults(user);
    }
    @Override
    public Object saveUsers(List<User> users){
        UserService.saveList(users);
        return ResponseUtil.createSuccessResults(users);
    }
}
