package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.Comment;
import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    List<Comment> findCommentsByCreator(User user);
    List<Comment> findCommentsByTextContains(String text);
}
