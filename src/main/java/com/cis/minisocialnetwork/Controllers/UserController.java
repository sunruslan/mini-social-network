package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Entities.User;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public @ResponseBody
    Optional<User> getUserById(@RequestParam(value = "user_id") Long id){
        return userRepository.findById(id);
    }


}
