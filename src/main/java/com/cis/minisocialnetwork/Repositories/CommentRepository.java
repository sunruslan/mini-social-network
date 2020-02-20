package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.Comment;
import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
    List<Comment> findCommentsByUserId(Long userId, Pageable pageable);
    List<Comment> findByPostId(Long postId, Pageable pageable);


}
