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
import com.cis.minisocialnetwork.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    public RestResponse getUser(@Valid @RequestParam("nickname") String user,
                                @Valid @RequestParam("password") String password){
        try{
            return RestResponse.createSuccessResponse(userService.fetchUserToken(user, password));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @GetMapping("/users")
    @ApiOperation(value = "get all users")
    public RestResponse getAllUsers(@Valid @RequestParam(value = "count", defaultValue = "10") int count,
                                  @Valid @RequestParam(value = "page", defaultValue = "1") int page) {

        try{
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            List<UserDto> users = userRepository.findAllUsers(username);
            int start = Math.max((page-1)*count, 0);
            int end = Math.min(page*count, users.size());
            return RestResponse.createSuccessResponse(users.subList(start, end));
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}