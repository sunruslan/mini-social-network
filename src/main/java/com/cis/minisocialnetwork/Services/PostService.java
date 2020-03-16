package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Post InsertPost(Post post, String username);

    ResponseEntity<?> deletePost(Long PostId);
}