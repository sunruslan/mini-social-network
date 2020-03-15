package com.cis.minisocialnetwork.Repositories;


import com.cis.minisocialnetwork.dto.PostDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<PostDto> findByUserNickname(String nick) {
        List<PostDto> resultList= new ArrayList<>();
        Query query = entityManager.createNativeQuery("select p.id,p.title,p.content,u.id as userId,u.profile_Pic_Url " +
                "from posts p,userprofile u, user_entity ey " +
                "where p.user_prof_id=u.id and ey.id = u.user_id and p.nickname=?","findAllDataMapping");
        query.setParameter(1, nick );
        resultList=query.getResultList();
        return resultList;
    }
}