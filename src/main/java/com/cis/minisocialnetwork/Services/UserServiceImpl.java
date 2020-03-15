package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Exception.CustomException;
import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import com.cis.minisocialnetwork.Security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String insertUser(User user){
        boolean flag;
        System.out.println(user);
        flag=alreadyRegistered(user.getNickname(), user.getPassword());
        if(!flag) {
            user.getUserProfile().setUser(user);
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getNickname(), user.getRoles());
        }
        else
        {
            throw new CustomException("User is already registered", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String fetchUserToken(String nickname, String password){
        User user;
        boolean flag;
        flag = alreadyRegistered(nickname, password);
        if(flag) {
            user = getUser(nickname, password);
            return jwtTokenProvider.createToken(user.getNickname(), user.getRoles());
        }
        else{
            throw new ResourceNotFoundException("User is not registered or password is incorrect");
        }
    }

    public User getUser(String nickname, String password) {
        User user;
        user= userRepository.getUserByNicknameAndPassword(nickname, password).get();
        return user;
    }

    @Override
    public boolean alreadyRegistered(String nickname, String password){
        return userRepository.existsByNicknameAndPassword(nickname, password);
    }
}