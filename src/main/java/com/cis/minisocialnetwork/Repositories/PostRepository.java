package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {
    Optional<Post> findById(Long id);
}
