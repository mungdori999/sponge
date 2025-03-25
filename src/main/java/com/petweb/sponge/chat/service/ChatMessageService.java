package com.petweb.sponge.chat.service;

import com.petweb.sponge.chat.domain.ChatMessage;
import com.petweb.sponge.chat.dto.ChatMessageCreate;
import com.petweb.sponge.chat.repository.ChatMessageRepository;
import com.petweb.sponge.utils.ClockHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ClockHolder clockHolder;
    private final ChatMessageRepository chatMessageRepository;

    public ChatMessage create(ChatMessageCreate chatMessageCreate, Long pubId, String loginType) {
        ChatMessage chatMessage = ChatMessage.from(chatMessageCreate, pubId, loginType, clockHolder);

        return chatMessageRepository.save(chatMessage);
    }
}
