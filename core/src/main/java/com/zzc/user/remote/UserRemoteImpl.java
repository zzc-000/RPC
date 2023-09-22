package com.zzc.user.remote;


import com.zzc.netty.util.ResponseUtil;
import com.zzc.user.model.User;

import javax.annotation.Resource;
import java.util.List;

//@Remote
public class UserRemoteImpl implements UserRemote{
    @Resource
    private com.zzc.user.service.UserService userService;


    @Override
    public Object saveUser(User user) {
        userService.save(user);
        return ResponseUtil.createSuccessResults(user);
    }

    @Override
    public Object saveUsers(List<User> users) {
        userService.saveList(users);
        return ResponseUtil.createSuccessResults(users);
    }
}
