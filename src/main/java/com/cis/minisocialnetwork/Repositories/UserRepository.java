package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findById(long id);
}
