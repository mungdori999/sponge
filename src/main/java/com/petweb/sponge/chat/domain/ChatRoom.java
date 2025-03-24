package com.petweb.sponge.chat.domain;

import com.petweb.sponge.chat.dto.ChatRoomCreate;
import com.petweb.sponge.utils.ClockHolder;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChatRoom {

    private Long id;
    private String lastChatMsg;
    private Long userId;
    private Long trainerId;
    private Long createdAt;

    @Builder
    public ChatRoom(Long id, String lastChatMsg, Long userId, Long trainerId, Long createdAt) {
        this.id = id;
        this.lastChatMsg = lastChatMsg;
        this.userId = userId;
        this.trainerId = trainerId;
        this.createdAt = createdAt;
    }

    public static ChatRoom from(ChatRoomCreate chatRoomCreate, Long loginId, ClockHolder clockHolder) {
        return ChatRoom.builder()
                .userId(loginId)
                .trainerId(chatRoomCreate.getTrainerId())
                .createdAt(clockHolder.clock())
                .build();
    }
}
