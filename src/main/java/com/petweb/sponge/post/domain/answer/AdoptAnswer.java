package com.petweb.sponge.post.domain.answer;

import lombok.Builder;
import lombok.Getter;

@Getter
public class AdoptAnswer {

    private Long id;
    private Long trainerId;

    private Long userId;

    private Long answerId;

    @Builder
    public AdoptAnswer(Long id, Long trainerId, Long userId, Long answerId) {
        this.id = id;
        this.trainerId = trainerId;
        this.userId = userId;
        this.answerId = answerId;
    }
}
