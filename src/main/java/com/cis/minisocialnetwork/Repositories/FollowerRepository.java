package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.Follower;
import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Long> {
    List<User> getAllByFrom(User from);
    Follower getByFromAndTo(User from, User to);
}
