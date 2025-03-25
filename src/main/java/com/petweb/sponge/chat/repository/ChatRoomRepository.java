package com.petweb.sponge.chat.repository;

import com.petweb.sponge.chat.domain.ChatRoom;

import java.util.List;

public interface ChatRoomRepository {
    List<ChatRoom> findListByTrainerId(Long loginId, int page);

    ChatRoom save(ChatRoom chatRoom);
}
