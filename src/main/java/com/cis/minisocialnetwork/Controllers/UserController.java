package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Entities.User;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getUserById(@RequestParam(value = "user_id") Long id){
        return new ResponseEntity<User>(userRepository.findById(id).get(), HttpStatus.OK);
    }


}
