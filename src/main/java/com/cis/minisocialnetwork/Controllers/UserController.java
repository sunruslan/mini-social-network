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
    public ResponseEntity<?> getAllUsers(@RequestParam(value = "count", defaultValue = "10") int count,
                                         @RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "term") String term){
        List<User> users = userRepository.findUsersByFirstNameContainsOrSecondNameContainsOrNicknameContains(term);
        int from = Math.max(0, (page - 1) * count);
        int to = Math.min(page * count, users.size());
        return new ResponseEntity<List<User>>(users.subList(from, to), HttpStatus.OK);

    }

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getUserById(@RequestParam(value = "user_id") Long id){
        return new ResponseEntity<User>(userRepository.findById(id).get(), HttpStatus.OK);
    }


}
