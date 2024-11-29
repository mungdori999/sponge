package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Post;

import java.util.List;
import java.util.Optional;

public interface PostQueryDslRepository {


    Optional<PostEntity> findPostById(Long id);
    List<PostEntity> findListByUserId(Long loginId);
    List<PostEntity> findListByKeyword(String keyword, int page);
    List<PostEntity> findListByCode(Long categoryCode, int page);
    void initPost(Long id);

}
