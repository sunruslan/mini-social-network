package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Post;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Model.UserProfile;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import com.cis.minisocialnetwork.Repositories.ProfileRepository;
import com.cis.minisocialnetwork.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    ProfileRepository userProfileRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Post InsertPost(Post post, String username){

        Optional<User> user = userRepository.getUserByNickname(username);
        if(user != null) {
            post.setUserProfile(user.get().getUserProfile());
            return post;
        }
        else{throw new ResourceNotFoundException("User Profile Is Not Found");
        }

        // return userProfileRepository.findById(UserProfId).map(post.s).orElseThrow(()->new ResourceNotFoundException("UserProfile Not found"));

    }
    @Override
    public ResponseEntity<?> deletePost(Long postId){
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}