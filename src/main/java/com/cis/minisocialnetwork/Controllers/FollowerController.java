package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.CustomException;
import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Followers;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.FollowerRepository;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import com.cis.minisocialnetwork.RestResponse;
import com.cis.minisocialnetwork.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value="minisocialnetwork", description="follow/unfollow")
public class FollowerController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowerRepository followerRepository;

    @GetMapping("/follow/{nickname}")
    @ApiOperation(value = "check if follow the user")
    public RestResponse isFollowing(@Valid @PathVariable("nickname") String nickname){
        try{
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            return RestResponse.createSuccessResponse(followerRepository.existsByFrom_NicknameAndTo_Nickname(username, nickname));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }

    @DeleteMapping("/follow/{nickname}")
    @ApiOperation(value = "unfollow the user")
    public RestResponse unfollow(@Valid @PathVariable("nickname") String nickname){
        try{
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            Optional<Followers> followers = followerRepository.findByFromNicknameAndToNickname(username, nickname);
            followers.ifPresent(value -> followerRepository.delete(value));
            return RestResponse.createSuccessResponse("OK");
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @PostMapping("/follow/{nickname}")
    @ApiOperation(value = "follow the user")
    public RestResponse follow(@Valid @PathVariable("nickname") String nickname){
        try{
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            User user1 = userRepository.getUserByNickname(username).get();
            User user2 = userRepository.getUserByNickname(nickname).get();
            return RestResponse.createSuccessResponse(followerRepository.save(new Followers(user1, user2)));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @GetMapping("/followings")
    @ApiOperation(value = "get all followings")
    public RestResponse getFollowings(){
        try{
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            List<UserDto> users = userRepository.getFollowings(username);
            return RestResponse.createSuccessResponse(users);
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}
