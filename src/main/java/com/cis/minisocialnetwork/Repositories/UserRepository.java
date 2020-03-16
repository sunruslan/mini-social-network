package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    @Override
    Optional<User> findById(Long UserId);

    boolean existsByNicknameAndPassword(String nickname, String password);

    User findByNickname(String nickname);

    @Query(value="select * from user_entity u where u.nickname=:nickname", nativeQuery = true)
    Optional<User> getUserByNickname(@Param("nickname") String nickname);

    @Query(value="select * from user_entity u where u.nickname=:nickname and u.password=:password", nativeQuery = true)
    Optional<User> getUserByNicknameAndPassword(@Param("nickname") String nickname, @Param("password") String password);
}
