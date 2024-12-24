package com.petweb.sponge.post.domain.answer;

import com.petweb.sponge.post.repository.answer.AnswerEntity;
import com.petweb.sponge.user.repository.UserEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "answer_recommend")
public class AnswerRecommend {
    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private Long answerId;

    @Builder
    public AnswerRecommend(Long id, Long userId, Long answerId) {
        this.id = id;
        this.userId = userId;
        this.answerId = answerId;
    }
}
