package com.petweb.sponge.post.dto.answer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerUpdate {
    private String content;
}
