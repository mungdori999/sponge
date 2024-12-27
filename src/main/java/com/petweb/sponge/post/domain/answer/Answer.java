package com.petweb.sponge.post.domain.answer;

import com.petweb.sponge.exception.error.LoginIdError;
import com.petweb.sponge.post.dto.answer.AnswerCreate;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Answer {

    private Long id;
    private String content; // 내용
    private int likeCount; // 추천수
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Long postId;
    private Long trainerId;

    @Builder
    public Answer(Long id, String content, int likeCount, Timestamp createdAt, Timestamp modifiedAt, Long postId, Long trainerId) {
        this.id = id;
        this.content = content;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.postId = postId;
        this.trainerId = trainerId;
    }

    public static Answer from(Long trainerId, Long postId, AnswerCreate answerCreate) {
        return Answer.builder()
                .content(answerCreate.getContent())
                .postId(postId)
                .trainerId(trainerId)
                .build();
    }

    public void checkTrainer(Long loginId) {
        if (!trainerId.equals(loginId)) {
            throw new LoginIdError();
        }
    }
}
