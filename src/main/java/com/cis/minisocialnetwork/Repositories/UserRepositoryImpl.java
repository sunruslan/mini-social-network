package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.dto.PostDto;
import com.cis.minisocialnetwork.dto.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserDto> findAllUsers(String nickname) {
        List<UserDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select u.nickname, up.about, up.gender, " +
                "exists(select * " +
                "from followers " +
                "where from_user_fk = " +
                "(" +
                "select id " +
                "from user_entity " +
                "where nickname = :nick" +
                ")" +
                "and to_user_fk = u.id" +
                "), up.location, up.profile_Pic_Url " +
                "from user_entity u,userprofile up " +
                "where up.user_id=u.id and u.nickname <> :nick","userMapping");
        query.setParameter("nick", nickname);
        resultList=query.getResultList();
        return resultList;
    }

    @Override
    public List<UserDto> getFollowings(String nickname) {
        List<UserDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select u.nickname, up.about, up.gender, TRUE, up.location, up.profile_Pic_Url " +
                "from user_entity u,userprofile up " +
                "where up.user_id=u.id and u.nickname <> :nick and exists(select * from followers where from_user_fk = (select id from user_entity where nickname = :nick) and to_user_fk = u.id)","userMapping");
        query.setParameter("nick", nickname);
        resultList=query.getResultList();
        return resultList;
    }
}