package com.cis.minisocialnetwork.Repositories;

import com.cis.minisocialnetwork.dto.PostDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostDto> findByUserProfileId(Long UserProdId);
}
