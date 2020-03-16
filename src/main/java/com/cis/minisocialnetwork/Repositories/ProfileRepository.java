package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<UserProfile,Long> {
    @Override
    Optional<UserProfile> findById(Long userId);
}
