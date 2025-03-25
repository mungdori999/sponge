package com.petweb.sponge.chat.repository;

import com.petweb.sponge.chat.domain.ChatMessage;


public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);
}
