package com.petweb.sponge.post.domain.answer;

import com.petweb.sponge.post.repository.answer.AnswerEntity;
import com.petweb.sponge.trainer.repository.TrainerEntity;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "adopt_answer")
public class AdoptAnswer {

    @Id
    @GeneratedValue
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
