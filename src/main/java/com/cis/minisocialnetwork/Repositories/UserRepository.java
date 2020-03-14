package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long UserId);

    boolean existsByNickname(String nickname);

    User findByNickname(String nickname);

    List<User> findAllByNicknameContains(String term);

    @Query(value="select * from user_entity u where u.nickname=:nickname", nativeQuery = true)
    Optional<User> getUserByNickname(@Param("nickname") String nickname);
}
