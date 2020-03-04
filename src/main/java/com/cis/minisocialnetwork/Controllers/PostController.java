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

    @RequestMapping(value = "/posts/like", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> like(@RequestParam(value = "post_id") Long id, @RequestParam(value = "rating") float rating){
        Post post = postRepository.findById(id).get();
        post.updateRating(rating);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> deletePost(@RequestParam(value = "post_id") Long id){
        postRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public ResponseEntity<?> createPost(@RequestBody Post post, UriComponentsBuilder builder){
        postRepository.save(post);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }
}
