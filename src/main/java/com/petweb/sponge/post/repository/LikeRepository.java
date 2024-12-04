package com.petweb.sponge.post.repository;

import com.petweb.sponge.post.domain.Like;
import com.petweb.sponge.post.repository.post.LikeEntity;

import java.util.Optional;

public interface LikeRepository {
    Optional<Like> findLike(Long postId, Long loginId);

    void save(Like newLike);
    void delete(Like like);

}
