package com.petweb.sponge.post.repository.post;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostQueryDslRepository {


    Optional<PostEntity> findPostById(Long id);

    List<PostEntity> findListByUserId(Long userId, int page);
    List<PostEntity> findListByKeyword(String keyword, int page);
    List<PostEntity> findListByCode(Long categoryCode, int page);
    void initPost(Long id);

    List<PostEntity> findListByPostListId(List<Long> postIdList);
}
