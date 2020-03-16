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
    public List<UserDto> findAllUsers() {
        List<UserDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select u.nickname, up.about, up.gender, up.location, up.profile_Pic_Url " +
                "from user_entity u,userprofile up " +
                "where up.user_id=u.id","userMapping");
        resultList=query.getResultList();
        return resultList;
    }
}