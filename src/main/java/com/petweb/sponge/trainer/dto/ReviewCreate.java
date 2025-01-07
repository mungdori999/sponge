package com.petweb.sponge.trainer.dto;

import lombok.Getter;

@Getter
public class ReviewCreate {

    private Long trainerId;
    private int score;
    private String content;
}
