package com.petweb.sponge.chat;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ChatMessage {
    private String type;  // 메시지 타입 (예: "TALK")
    private String roomId;  // 채팅방 ID
    private String sender;  // 보낸 사람
    private String message; // 메시지 내용
}
