package com.petweb.sponge.post.domain.post;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Bookmark {

    private Long id;
    private Long postId;
    private Long userId;
    private Timestamp createdAt;

    @Builder

    public Bookmark(Long id, Long postId, Long userId, Timestamp createdAt) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public static Bookmark from(Long postId, Long loginId) {
        return Bookmark.builder()
                .postId(postId)
                .userId(loginId)
                .build();
    }
}
