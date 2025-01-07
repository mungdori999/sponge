package com.petweb.sponge.trainer.domain;

import com.petweb.sponge.trainer.dto.ReviewCreate;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Review {

    private Long id;

    private float score;
    private String content;

    private Timestamp createdAt;

    private Long trainerId;

    private Long userId;

    @Builder
    public Review(Long id, float score, String content, Timestamp createdAt, Long trainerId, Long userId) {
        this.id = id;
        this.score = score;
        this.content = content;
        this.createdAt = createdAt;
        this.trainerId = trainerId;
        this.userId = userId;
    }

    public static Review from(Long userId, ReviewCreate reviewCreate) {
        return Review.builder()
                .score(reviewCreate.getScore())
                .content(reviewCreate.getContent())
                .trainerId(reviewCreate.getTrainerId())
                .userId(userId)
                .build();
    }
}
