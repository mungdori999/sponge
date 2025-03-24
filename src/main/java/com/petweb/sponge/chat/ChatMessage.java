package com.petweb.sponge.chat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessage {
    private String type;  // 메시지 타입 (예: "TALK")
    private String roomId;  // 채팅방 ID
    private String loginType;  // 보낸 사람
    private String id; // 유저,훈련사 ID
    private String message; // 메시지 내용
}
