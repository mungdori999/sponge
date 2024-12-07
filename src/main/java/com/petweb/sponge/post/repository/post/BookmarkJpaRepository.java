package com.petweb.sponge.post.repository.post;

import com.petweb.sponge.post.domain.post.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookmarkJpaRepository extends JpaRepository<BookmarkEntity,Long> {


    @Query("SELECT be FROM BookmarkEntity be WHERE be.postId = :postId AND be.userId = :loginId")
    Optional<BookmarkEntity> findBookmark(@Param("postId") Long postId, @Param("loginId") Long loginId);
}
