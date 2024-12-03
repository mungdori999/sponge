package com.petweb.sponge.post.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Like {

    private Long id;
    private Long postId;
    private Long userId;

    @Builder
    public Like(Long id, Long postId, Long userId) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
    }
}
