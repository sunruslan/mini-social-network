package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Post;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import com.cis.minisocialnetwork.Repositories.ProfileRepository;
import com.cis.minisocialnetwork.RestResponse;
import com.cis.minisocialnetwork.Services.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;


@Api(value="minisocialnetwork", description="Operations pertaining to posts(Creation,deletion and manupulation)")
@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    ProfileRepository userProfileRepository;

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    @ApiOperation(value = "get all posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts/{userprofileid}")
    public RestResponse createPost(@Valid @RequestBody Post post, @PathVariable Long userprofileid) {
        try {
            postService.InsertPost(post, userprofileid);
            return RestResponse.createSuccessResponse(postRepository.save(post));
        }

        catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @PutMapping("/posts/{postId}")
    @ApiOperation(value = "Update a post based on the post id")
    public RestResponse<?> updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {

        try{
            return RestResponse.createSuccessResponse(postService.EditPost(postId,postRequest));

        }catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }


    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "Delete a post based on the postid")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {


        try{return RestResponse.createSuccessResponse(postService.deletePost(postId)); }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

}