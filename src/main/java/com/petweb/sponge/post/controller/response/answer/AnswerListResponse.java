package com.petweb.sponge.post.controller.response.answer;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class AnswerListResponse {

    private AnswerResponse answerResponse;
    private TrainerShortResponse trainerShortResponse;
    private boolean checkAdopt;

    public static AnswerListResponse from(AnswerResponse answerResponse,TrainerShortResponse trainerShortResponse,boolean checkAdopt) {
        return AnswerListResponse.builder()
                .answerResponse(answerResponse)
                .trainerShortResponse(trainerShortResponse)
                .checkAdopt(checkAdopt)
                .build();
    }
}
