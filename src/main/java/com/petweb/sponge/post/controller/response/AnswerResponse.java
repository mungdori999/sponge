package com.petweb.sponge.post.controller.response;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class AnswerResponse {
    private Long id;
    private String content; // 내용
    private int likeCount; // 추천수
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    private Long postId;
    private Long trainerId;
}
