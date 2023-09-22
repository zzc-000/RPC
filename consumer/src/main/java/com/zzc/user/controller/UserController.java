package com.zzc.user.controller;//package com.zzc.netty.user.controller;
//
//import com.zzc.netty.user.bean.User;
//import com.zzc.netty.user.service.UserService;
//import com.zzc.netty.util.Response;
//import com.zzc.netty.util.ResponseUtil;
//import org.springframework.stereotype.Controller;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Controller
//public class UserController {
//    @Resource
//    private UserService userService;
//    public Response saveUser(User user){
//        UserService.save(user);
//        return ResponseUtil.createSuccessResults(user);
//    }
//    public Response saveUsers(List<User> users){
//        UserService.saveList(users);
//        return ResponseUtil.createSuccessResults(users);
//    }
//}
