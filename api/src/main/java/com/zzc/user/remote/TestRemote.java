package com.zzc.user.remote;

import com.zzc.user.model.User;

import java.util.List;

public interface TestRemote {
        public Object testUser(User user);

        public Object testUsers(List<User> users);
}

