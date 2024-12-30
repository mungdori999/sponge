package com.petweb.sponge.post.domain.answer;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AnswerLike {

    private Long id;
    private Long answerId;
    private Long userId;

    @Builder
    public AnswerLike(Long id, Long answerId, Long userId) {
        this.id = id;
        this.answerId = answerId;
        this.userId = userId;
    }
}
