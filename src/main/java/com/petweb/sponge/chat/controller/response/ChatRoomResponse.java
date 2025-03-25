package com.petweb.sponge.chat.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomResponse {


    private String name;
    private String imgUrl;
    private String lastMsg;
    private Long createdAt;


    public static ChatRoomResponse from(String name, String imgUrl, String lastMsg, Long createdAt) {
        return ChatRoomResponse.builder()
                .name(name)
                .imgUrl(imgUrl)
                .lastMsg(lastMsg)
                .createdAt(createdAt).build();
    }
}
