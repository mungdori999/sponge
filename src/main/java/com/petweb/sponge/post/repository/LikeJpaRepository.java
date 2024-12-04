package com.petweb.sponge.post.repository;

import com.petweb.sponge.post.repository.post.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikeJpaRepository extends JpaRepository<LikeEntity,Long> {


    @Query("SELECT l FROM LikeEntity l WHERE l.postId = :postId AND l.userId = :loginId")
    Optional<LikeEntity> findLike(@Param("postId") Long postId, @Param("loginId") Long loginId);
}
