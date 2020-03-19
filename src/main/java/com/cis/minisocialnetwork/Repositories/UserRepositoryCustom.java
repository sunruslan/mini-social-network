package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.dto.PostDto;
import com.cis.minisocialnetwork.dto.UserDto;

import java.util.List;

public interface UserRepositoryCustom {
    List<UserDto> findAllUsers(String nickname);
    List<UserDto> getFollowings(String nickname);
}
