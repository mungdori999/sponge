package com.petweb.sponge.post.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostRecommendRepository extends JpaRepository<LikeEntity,Long> {

    @Query("SELECT pr FROM LikeEntity pr WHERE pr.postEntity.id = :problemPostId AND pr.userId = :userId")
    Optional<LikeEntity> findRecommend(@Param("problemPostId") Long problemPostId, @Param("userId") Long userId);
}
