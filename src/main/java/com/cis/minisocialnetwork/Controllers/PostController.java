package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Entities.Post;
import com.cis.minisocialnetwork.Repositories.FollowerRepository;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;


    @RequestMapping(value = "/posts", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> deletePost(@RequestParam(value = "post_id") Long id){

    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> createPost(@RequestBody Post post, UriComponentsBuilder builder){

    }
}
