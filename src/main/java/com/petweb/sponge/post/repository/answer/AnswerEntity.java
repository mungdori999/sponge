package com.petweb.sponge.post.repository.answer;

import com.petweb.sponge.post.domain.answer.Answer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "answer")
@EntityListeners(AuditingEntityListener.class)
public class AnswerEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String content; // 내용
    private int likeCount; // 추천수
    @CreatedDate
    private Timestamp createdAt;
    @LastModifiedDate
    private Timestamp modifiedAt;
    private Long postId;

    private Long trainerId;

    public static AnswerEntity from(Answer answer) {
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.id = answer.getId();
        answerEntity.content = answer.getContent();
        answerEntity.likeCount = answer.getLikeCount();
        answerEntity.createdAt = answer.getCreatedAt();
        answerEntity.modifiedAt = answer.getModifiedAt();
        answerEntity.postId = answer.getPostId();
        answerEntity.trainerId = answer.getTrainerId();
        return answerEntity;
    }

    public Answer toModel() {
        return Answer.builder()
                .id(id)
                .content(content)
                .likeCount(likeCount)
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .postId(postId)
                .trainerId(trainerId)
                .build();
    }
}
