package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.Followers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowerRepository extends JpaRepository<Followers, Long> {
    boolean existsByFrom_NicknameAndTo_Nickname(String nick1, String nick2);
    void deleteByFrom_NicknameAndTo_Nickname(String nick1, String nick2);
}
