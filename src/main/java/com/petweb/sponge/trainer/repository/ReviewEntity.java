package com.petweb.sponge.trainer.repository;

import com.petweb.sponge.trainer.domain.Review;
import com.petweb.sponge.trainer.repository.TrainerEntity;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "review")
@EntityListeners(AuditingEntityListener.class)
public class ReviewEntity {

    @Id
    @GeneratedValue
    private Long id;

    private float score;
    private String content;

    @CreatedDate
    private Timestamp createdAt;

    private Long trainerId;

    private Long userId;

    @Builder
    public ReviewEntity(Long id, float score, String content, Timestamp createdAt, Long trainerId, Long userId) {
        this.id = id;
        this.score = score;
        this.content = content;
        this.createdAt = createdAt;
        this.trainerId = trainerId;
        this.userId = userId;
    }
    public static ReviewEntity from(Review review) {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.id = review.getId();
        reviewEntity.score = review.getScore();
        reviewEntity.content = review.getContent();
        reviewEntity.createdAt = review.getCreatedAt();
        reviewEntity.trainerId = review.getTrainerId();
        reviewEntity.userId = review.getUserId();
        return reviewEntity;
    }
    public Review toModel() {
        return Review.builder()
                .id(id)
                .score(score)
                .content(content)
                .createdAt(createdAt)
                .trainerId(trainerId)
                .userId(userId)
                .build();
    }
}
