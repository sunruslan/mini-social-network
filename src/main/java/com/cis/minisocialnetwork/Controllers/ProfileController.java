package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.CustomException;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.ProfileRepository;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import com.cis.minisocialnetwork.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value="minisocialnetwork", description="Operations pertaining to profile")
@RestController
public class ProfileController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/profile/{nickname}")
    @ApiOperation("get user profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public RestResponse getUserProfileByNickname(@Valid @PathVariable("nickname") String nickname){
        try{
            return RestResponse.createSuccessResponse(userRepository.getProfile(nickname));
        }
        catch(CustomException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }
}
