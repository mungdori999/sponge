package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.AdoptAnswer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "adopt_answer")
public class AdoptAnswerEntity {

    @Id
    @GeneratedValue
    private Long id;


    private Long trainerId;

    private Long userId;

    private Long answerId;

    @Builder
    public AdoptAnswerEntity(Long id, Long trainerId, Long userId, Long answerId) {
        this.id = id;
        this.trainerId = trainerId;
        this.userId = userId;
        this.answerId = answerId;
    }

     public AdoptAnswer toModel() {
        return AdoptAnswer.builder()
                .id(id)
                .trainerId(trainerId)
                .userId(userId)
                .answerId(answerId).build();
     }
}
