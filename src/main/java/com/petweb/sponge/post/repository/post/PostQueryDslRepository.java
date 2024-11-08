package com.petweb.sponge.post.repository.post;

import java.util.List;
import java.util.Optional;

public interface PostQueryDslRepository {


    Optional<PostEntity> findPostById(Long id);
    List<PostEntity> findListByCode(Long problemTypeCode, int page);
}
