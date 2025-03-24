package com.petweb.sponge.chat.repository;

import com.petweb.sponge.chat.domain.ChatRoom;

public interface ChatRoomRepository {
    ChatRoom save(ChatRoom chatRoom);
}
