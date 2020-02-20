package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.Post;
import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findById(Long id);
    List<Post> findPostsByTitleContainsOrTextContains(String title, String text);
    List<Post> findPostsByUser(User user);
    List<Post> findPostsByRatingBetween(int a, int b);


}
