package com.petweb.sponge.trainer.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class ReviewResponse {

    private Long id;
    private int score;
    private String content;
    private Timestamp createdAt;
    private String userName;
}
