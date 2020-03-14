package com.cis.minisocialnetwork.Services;

import com.cis.minisocialnetwork.Model.Post;
import org.springframework.http.ResponseEntity;

public interface PostService {

    Post InsertPost(Post post,Long UserProfId);

    Post EditPost(Long postId, Post post);

    ResponseEntity<?> deletePost(Long PostId);
}