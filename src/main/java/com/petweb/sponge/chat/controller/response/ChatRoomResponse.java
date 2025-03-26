package com.petweb.sponge.chat.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatRoomResponse {


    private String name;
    private String loginType;
    private String imgUrl;
    private String lastMsg;
    private Long createdAt;


    public static ChatRoomResponse from(String name, String imgUrl,String loginType, String lastMsg, Long createdAt) {
        return ChatRoomResponse.builder()
                .name(name)
                .imgUrl(imgUrl)
                .loginType(loginType)
                .lastMsg(lastMsg)
                .createdAt(createdAt).build();
    }
}
