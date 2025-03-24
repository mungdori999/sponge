package com.petweb.sponge.chat.dto;

import lombok.Getter;

@Getter
public class ChatMessageCreate {


    private Long chatRoomId;
    private String message;
}
