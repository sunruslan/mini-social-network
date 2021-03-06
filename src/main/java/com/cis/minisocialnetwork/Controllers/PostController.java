package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Exception.ResourceNotFoundException;
import com.cis.minisocialnetwork.Model.Post;
import com.cis.minisocialnetwork.Model.User;
import com.cis.minisocialnetwork.Repositories.PostRepository;
import com.cis.minisocialnetwork.Repositories.ProfileRepository;
import com.cis.minisocialnetwork.RestResponse;
import com.cis.minisocialnetwork.Services.PostService;
import com.cis.minisocialnetwork.dto.PostDto;
import com.cis.minisocialnetwork.dto.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.vavr.Tuple2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


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
    @CrossOrigin(origins = "http://localhost:3000")
    public RestResponse getAllPosts(@Valid @RequestParam(value = "count", defaultValue = "10") int count,
                                  @Valid @RequestParam(value = "page", defaultValue = "1") int page) {
        try{
            List<PostDto> posts = postRepository.findAllPosts();
            int start = Math.max((page-1)*count, 0);
            int end = Math.min(page*count, posts.size());
            Tuple2<Integer, List<PostDto>> response = new Tuple2<>(posts.size(), posts.subList(start, end));
            return RestResponse.createSuccessResponse(response);
        }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @GetMapping("/posts/{nickname}")
    @ApiOperation(value = "Get all posts of the user")
    @CrossOrigin(origins = "http://localhost:3000")
    public RestResponse getAllPosts(@Valid @PathVariable("nickname") String nickname,
                                     @Valid @RequestParam(value = "count", defaultValue = "10") int count,
                                     @Valid @RequestParam(value = "page", defaultValue = "1") int page){
        List<PostDto> posts= postRepository.findByUserNickname(nickname);
        int start = Math.max((page-1)*count, 0);
        int end = Math.min(page*count, posts.size());
        Tuple2<Integer, List<PostDto>> response = new Tuple2<>(posts.size(), posts.subList(start, end));
        return RestResponse.createSuccessResponse(response);
    }

    @PostMapping("/posts")
    @ApiOperation(value = "create new post")
    @CrossOrigin(origins = "http://localhost:3000")
    public RestResponse createPost(@Valid @RequestBody Post post) {
        try {
            String username = "";
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails)principal).getUsername();
            } else {
                username = principal.toString();
            }
            postService.InsertPost(post, username);
            return RestResponse.createSuccessResponse(postRepository.save(post));
        }

        catch (ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

    @DeleteMapping("/posts/{postId}")
    @ApiOperation(value = "Delete a post based on the postid")
    @CrossOrigin(origins = "http://localhost:3000")
    public RestResponse<?> deletePostOfUser(@PathVariable Long postId) {
        try{return RestResponse.createSuccessResponse(postService.deletePost(postId)); }
        catch(ResourceNotFoundException e){
            return RestResponse.createFailureResponse(e.getMessage(),400);
        }
    }

}