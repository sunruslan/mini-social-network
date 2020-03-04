package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Entities.Follower;
import com.cis.minisocialnetwork.Entities.User;
import com.cis.minisocialnetwork.Repositories.FollowerRepository;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FollowerController {
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/followers", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> getFollowersById(@RequestParam(value = "user_id") Long id){
        Optional<User> user = userRepository.findById(id);
        List<User> followers = followerRepository.getAllByFrom(user.get()).stream().map(f -> f.getTo()).collect(Collectors.toList());
        return new ResponseEntity<List<User>>(followers, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscribe", method=RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> subscribe(@RequestParam(value = "from_id") Long from_id, @RequestParam(value = "to_id") Long to_id) {
        User from_user = userRepository.findById(from_id).get();
        User to_user = userRepository.findById(to_id).get();
        followerRepository.save(new Follower(from_user, to_user));
        List<User> followers = followerRepository.getAllByFrom(from_user).stream().map(f -> f.getTo()).collect(Collectors.toList());
        return new ResponseEntity<List<User>>(followers, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscribe", method=RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> unsubscribe(@RequestParam(value = "from_id") Long from_id, @RequestParam(value = "to_id") Long to_id) {
        User from_user = userRepository.findById(from_id).get();
        User to_user = userRepository.findById(to_id).get();
        Follower follower = followerRepository.getByFromAndTo(from_user, to_user);
        followerRepository.delete(follower);
        List<User> followers = followerRepository.getAllByFrom(from_user).stream().map(f -> f.getTo()).collect(Collectors.toList());
        return new ResponseEntity<List<User>>(followers, HttpStatus.OK);
    }
}
