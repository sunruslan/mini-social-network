package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Post;
import com.cis.minisocialnetwork.Model.UserProfile;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import com.cis.minisocialnetwork.Repositories.ProfileRepository;
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

    @Override
    public Post InsertPost(Post post, Long UserProfId){

        Optional<UserProfile> userProfile = userProfileRepository.findById(UserProfId);
        if(userProfile != null) {
            post.setUserProfile(userProfile.get());
            return post;
        }
        else{throw new ResourceNotFoundException("User Profile Is Not Found");
        }

        // return userProfileRepository.findById(UserProfId).map(post.s).orElseThrow(()->new ResourceNotFoundException("UserProfile Not found"));

    }

    @Override
    public Post EditPost(Long postId,Post post){

        return postRepository.findById(postId).map(Updatedpost -> {
            Updatedpost.setTitle(post.getTitle());
            Updatedpost.setContent(post.getContent());
            return postRepository.save(Updatedpost);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));

    }

    @Override
    public ResponseEntity<?> deletePost(Long postId){
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}