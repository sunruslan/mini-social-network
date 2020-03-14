package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.CustomException;
import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Post;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import com.cis.minisocialnetwork.RestResponse;
import com.cis.minisocialnetwork.Services.UserService;
import com.cis.minisocialnetwork.dto.PostDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@RestController
@Api(value="minisocialnetwork", description="Operations pertaining to user)")
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;


    @PostMapping("/users/signup")
    @ApiOperation(value = "Signup for the user")
    public RestResponse createUser(@Valid @RequestBody User user){
        try{
            return RestResponse.createSuccessResponse(userService.insertUser(user));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @GetMapping("/users/signin")
    @ApiOperation(value = "Signing in of the user")
    public RestResponse getUser(@Valid @RequestParam("nickname") String user){
        try{
            return RestResponse.createSuccessResponse(userService.fetchUserToken(user));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }


    @GetMapping("/users/getall")
    @ApiOperation(value = "Get all posts of the user")
    public List<PostDto> getAllPosts(@Valid @RequestParam("userProfId") Long userProfId){
        List<Post> posts= new ArrayList<>();
        return postRepository.findByUserProfileId(userProfId);
    }

    @GetMapping("/users/")
    @ApiOperation(value = "get all users")
    public RestResponse getAllUsers(@Valid @RequestParam(value = "count", defaultValue = "10") int count,
                                  @Valid @RequestParam(value = "page", defaultValue = "1") int page,
                                  @Valid @RequestParam(value = "term", defaultValue = "") String term) {

        try{
            int start = (page-1)*count;
            int end = page*count;
            List<User> users = userRepository.findAllByNicknameContains(term).subList(start, end);
            return RestResponse.createSuccessResponse(users);
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }

    }
}