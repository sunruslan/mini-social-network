package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Followers, Long> {
    boolean existsByFrom_NicknameAndTo_Nickname(String nick1, String nick2);
    Optional<Followers> findByFromNicknameAndToNickname(String from, String to);
}
