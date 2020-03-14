package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Model.User;

public interface UserService {
    String insertUser(User user);
    String fetchUserToken(String user);
    boolean alreadyRegistered(String nickname);
}
