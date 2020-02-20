package com.cis.minisocialnetwork.Controllers;

import com.cis.minisocialnetwork.Entities.Comment;
import com.cis.minisocialnetwork.Repositories.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private CommentRepository commentRepository;
    private PostController postController;

    @GetMapping("/posts/{postId}/comments")
    public Page<Comment> getCommentsByPostId(@PathVariable(value="postId") Long postId, Pageable pageable){
        return commentRepository
    }
}
