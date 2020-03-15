package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Model.User;

public interface UserService {
    String insertUser(User user);
    String fetchUserToken(String user, String password);
    boolean alreadyRegistered(String nickname, String password);
}
